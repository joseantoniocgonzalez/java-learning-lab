package org.jose.learning.api.book;

import org.jose.learning.api.book.dto.BookResponse;
import org.jose.learning.api.book.dto.CreateBookRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public Page<BookResponse> list(Pageable pageable) {
        return bookRepository.findAll(pageable)
                .map(b -> new BookResponse(b.getId(), b.getTitle(), b.getAuthor(), b.getYear()));
    }

    public BookResponse getById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));

        return new BookResponse(book.getId(), book.getTitle(), book.getAuthor(), book.getYear());
    }

    public BookResponse update(Long id, CreateBookRequest request) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));

        book.setTitle(request.title());
        book.setAuthor(request.author());
        book.setYear(request.year());

        Book saved = bookRepository.save(book);
        return new BookResponse(saved.getId(), saved.getTitle(), saved.getAuthor(), saved.getYear());
    }

    public void delete(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        }
        bookRepository.deleteById(id);
    }
}
