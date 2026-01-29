package org.jose.learning.api.item;

import org.jose.learning.api.item.dto.CreateItemRequest;
import org.jose.learning.api.item.dto.ItemResponse;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ItemServiceTest {

    @Test
    void createShouldSaveAndReturnResponse() {
        ItemRepository repo = mock(ItemRepository.class);
        ItemService service = new ItemService(repo);

        Item saved = new Item("Item1");
        // simulate DB setting an id (we can't set id directly, so we return an Item with null id
        // and only assert name + that save was called with correct value)
        when(repo.save(any(Item.class))).thenReturn(saved);

        ItemResponse res = service.create(new CreateItemRequest("Item1"));

        assertEquals("Item1", res.name());
        verify(repo, times(1)).save(any(Item.class));
    }

    @Test
    void listShouldReturnMappedResponses() {
        ItemRepository repo = mock(ItemRepository.class);
        ItemService service = new ItemService(repo);

        when(repo.findAll()).thenReturn(List.of(new Item("A"), new Item("B")));

        List<ItemResponse> res = service.list();

        assertEquals(2, res.size());
        assertEquals("A", res.get(0).name());
        assertEquals("B", res.get(1).name());
        verify(repo, times(1)).findAll();
    }

    @Test
    void getByIdShouldReturnResponseWhenExists() {
        ItemRepository repo = mock(ItemRepository.class);
        ItemService service = new ItemService(repo);

        Item item = new Item("X");
        when(repo.findById(1L)).thenReturn(Optional.of(item));

        ItemResponse res = service.getById(1L);

        assertEquals("X", res.name());
        verify(repo).findById(1L);
    }

    @Test
    void getByIdShouldThrow404WhenMissing() {
        ItemRepository repo = mock(ItemRepository.class);
        ItemService service = new ItemService(repo);

        when(repo.findById(1L)).thenReturn(Optional.empty());

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> service.getById(1L));
        assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
        assertEquals("Item not found", ex.getReason());
    }

    @Test
    void updateShouldSaveNewName() {
        ItemRepository repo = mock(ItemRepository.class);
        ItemService service = new ItemService(repo);

        Item item = new Item("Old");
        when(repo.findById(1L)).thenReturn(Optional.of(item));
        when(repo.save(any(Item.class))).thenAnswer(inv -> inv.getArgument(0));

        ItemResponse res = service.update(1L, new CreateItemRequest("New"));

        assertEquals("New", res.name());

        ArgumentCaptor<Item> captor = ArgumentCaptor.forClass(Item.class);
        verify(repo).save(captor.capture());
        assertEquals("New", captor.getValue().getName());
    }

    @Test
    void updateShouldThrow404WhenMissing() {
        ItemRepository repo = mock(ItemRepository.class);
        ItemService service = new ItemService(repo);

        when(repo.findById(1L)).thenReturn(Optional.empty());

        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
                () -> service.update(1L, new CreateItemRequest("X")));

        assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
        assertEquals("Item not found", ex.getReason());
    }

    @Test
    void deleteShouldDeleteWhenExists() {
        ItemRepository repo = mock(ItemRepository.class);
        ItemService service = new ItemService(repo);

        when(repo.existsById(1L)).thenReturn(true);

        service.delete(1L);

        verify(repo).deleteById(1L);
    }

    @Test
    void deleteShouldThrow404WhenMissing() {
        ItemRepository repo = mock(ItemRepository.class);
        ItemService service = new ItemService(repo);

        when(repo.existsById(1L)).thenReturn(false);

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> service.delete(1L));
        assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
        assertEquals("Item not found", ex.getReason());
        verify(repo, never()).deleteById(anyLong());
    }
}
