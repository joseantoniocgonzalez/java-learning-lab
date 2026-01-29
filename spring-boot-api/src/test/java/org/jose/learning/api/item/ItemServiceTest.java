package org.jose.learning.api.item;

import org.jose.learning.api.item.dto.CreateItemRequest;
import org.jose.learning.api.item.dto.ItemResponse;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ItemServiceTest {

    private final ItemRepository itemRepository = mock(ItemRepository.class);
    private final ItemService itemService = new ItemService(itemRepository);

    private static Item itemWithId(Long id, String name) {
        try {
            Item item = new Item(name);
            Field idField = Item.class.getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(item, id);
            return item;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void shouldCreateItem() {
        when(itemRepository.save(any(Item.class))).thenReturn(itemWithId(1L, "Item1"));

        ItemResponse created = itemService.create(new CreateItemRequest("Item1"));

        assertThat(created.id()).isEqualTo(1L);
        assertThat(created.name()).isEqualTo("Item1");
    }

    @Test
    void shouldListItemsPaged() {
        when(itemRepository.findAll(any(PageRequest.class)))
                .thenReturn(new PageImpl<>(List.of(
                        itemWithId(1L, "Item1"),
                        itemWithId(2L, "Item2")
                )));

        Page<ItemResponse> page = itemService.list(PageRequest.of(0, 20));

        assertThat(page.getContent()).hasSize(2);
        assertThat(page.getContent().get(0).name()).isEqualTo("Item1");
        assertThat(page.getContent().get(1).name()).isEqualTo("Item2");
    }

    @Test
    void shouldThrowNotFoundWhenGetMissing() {
        when(itemRepository.findById(999L)).thenReturn(Optional.empty());

        Exception ex = assertThrows(Exception.class, () -> itemService.getById(999L));
        assertThat(ex.getMessage()).contains("Item not found");
    }

    @Test
    void shouldDeleteExisting() {
        when(itemRepository.existsById(1L)).thenReturn(true);

        itemService.delete(1L);

        verify(itemRepository).deleteById(1L);
    }
}
