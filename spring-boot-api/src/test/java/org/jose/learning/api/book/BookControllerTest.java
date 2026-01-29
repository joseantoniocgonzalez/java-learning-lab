package org.jose.learning.api.book;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void cleanDatabase() {
        bookRepository.deleteAll();
    }

    @Test
    void shouldCreateBook() throws Exception {
        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Dune\",\"author\":\"Frank Herbert\",\"year\":1965}"))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.title").value("Dune"))
                .andExpect(jsonPath("$.author").value("Frank Herbert"))
                .andExpect(jsonPath("$.year").value(1965));
    }

    @Test
    void shouldReturnBadRequestWhenTitleBlank() throws Exception {
        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"\",\"author\":\"Frank Herbert\",\"year\":1965}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").exists());
    }

    @Test
    void shouldReturnBadRequestWhenYearOutOfRange() throws Exception {
        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Dune\",\"author\":\"Frank Herbert\",\"year\":1200}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").exists());
    }

    @Test
    void shouldListBooks() throws Exception {
        bookRepository.save(new Book("Dune", "Frank Herbert", 1965));
        bookRepository.save(new Book("Neuromancer", "William Gibson", 1984));

        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void shouldGetBookByIdWhenExists() throws Exception {
        Book saved = bookRepository.save(new Book("Dune", "Frank Herbert", 1965));

        mockMvc.perform(get("/api/books/" + saved.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(saved.getId()))
                .andExpect(jsonPath("$.title").value("Dune"))
                .andExpect(jsonPath("$.author").value("Frank Herbert"))
                .andExpect(jsonPath("$.year").value(1965));
    }

    @Test
    void shouldReturnNotFoundWithJsonWhenMissingBook() throws Exception {
        mockMvc.perform(get("/api/books/999999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Book not found"));
    }
}
