package org.jose.learning.api.book;

import org.jose.learning.api.book.dto.BookResponse;
import org.jose.learning.api.book.dto.CreateBookRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookResponse create(CreateBookRequest request) {
        Book saved = bookRepository.save(new Book(request.title(), request.author(), request.year()));
        return new BookResponse(saved.getId(), saved.getTitle(), saved.getAuthor(), saved.getYear());
    }

    public List<BookResponse> list() {
        return bookRepository.findAll()
                .stream()
                .map(b -> new BookResponse(b.getId(), b.getTitle(), b.getAuthor(), b.getYear()))
                .toList();
    }

    public BookResponse getById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));

        return new BookResponse(book.getId(), book.getTitle(), book.getAuthor(), book.getYear());
    }
}
