package org.jose.learning.api.item;

import org.jose.learning.api.item.dto.CreateItemRequest;
import org.jose.learning.api.item.dto.ItemResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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

    public List<ItemResponse> list() {
        return itemRepository.findAll()
                .stream()
                .map(i -> new ItemResponse(i.getId(), i.getName()))
                .toList();
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
