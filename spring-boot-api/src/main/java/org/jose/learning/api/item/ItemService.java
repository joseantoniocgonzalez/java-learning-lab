package org.jose.learning.api.item;

import org.jose.learning.api.item.dto.CreateItemRequest;
import org.jose.learning.api.item.dto.ItemResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public ItemResponse create(CreateItemRequest request) {
        Item saved = itemRepository.save(new Item(request.name()));
        return new ItemResponse(saved.getId(), saved.getName());
    }

    public Page<ItemResponse> list(Pageable pageable) {
        return itemRepository.findAll(pageable)
                .map(i -> new ItemResponse(i.getId(), i.getName()));
    }

    public ItemResponse getById(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found"));

        return new ItemResponse(item.getId(), item.getName());
    }

    public ItemResponse update(Long id, CreateItemRequest request) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found"));

        item.setName(request.name());
        Item saved = itemRepository.save(item);

        return new ItemResponse(saved.getId(), saved.getName());
    }

    public void delete(Long id) {
        if (!itemRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found");
        }
        itemRepository.deleteById(id);
    }
}
