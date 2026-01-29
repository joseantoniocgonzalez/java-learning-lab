package org.jose.learning.api.book;

import jakarta.validation.Valid;
import org.jose.learning.api.book.dto.BookResponse;
import org.jose.learning.api.book.dto.CreateBookRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookResponse> create(@Valid @RequestBody CreateBookRequest request) {
        BookResponse created = bookService.create(request);
        return ResponseEntity.created(URI.create("/api/books/" + created.id())).body(created);
    }

    @GetMapping
    public Page<BookResponse> list(Pageable pageable) {
        return bookService.list(pageable);
    }

    @GetMapping("/{id}")
    public BookResponse getById(@PathVariable Long id) {
        return bookService.getById(id);
    }

    @PutMapping("/{id}")
    public BookResponse update(@PathVariable Long id, @Valid @RequestBody CreateBookRequest request) {
        return bookService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
