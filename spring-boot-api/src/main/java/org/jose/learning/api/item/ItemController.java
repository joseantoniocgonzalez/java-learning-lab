package org.jose.learning.api.item;

import jakarta.validation.Valid;
import org.jose.learning.api.item.dto.CreateItemRequest;
import org.jose.learning.api.item.dto.ItemResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemRepository itemRepository;

    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @PostMapping
    public ResponseEntity<ItemResponse> create(@Valid @RequestBody CreateItemRequest request) {
        Item saved = itemRepository.save(new Item(request.name()));
        ItemResponse response = new ItemResponse(saved.getId(), saved.getName());
        return ResponseEntity.created(URI.create("/api/items/" + saved.getId())).body(response);
    }

    @GetMapping
    public List<ItemResponse> list() {
        return itemRepository.findAll()
                .stream()
                .map(i -> new ItemResponse(i.getId(), i.getName()))
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemResponse> getById(@PathVariable Long id) {
        return itemRepository.findById(id)
                .map(i -> ResponseEntity.ok(new ItemResponse(i.getId(), i.getName())))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
