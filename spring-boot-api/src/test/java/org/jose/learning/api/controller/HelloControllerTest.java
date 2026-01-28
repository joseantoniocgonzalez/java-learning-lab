package org.jose.learning.api.controller;

import org.jose.learning.api.dto.HelloResponse;
import org.jose.learning.api.service.HelloService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HelloController.class)
class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HelloService helloService;

    @Test
    void shouldReturnDefaultHelloWhenNameMissing() throws Exception {
        given(helloService.getHello()).willReturn(new HelloResponse("Hello from Spring Boot"));

        mockMvc.perform(get("/api/hello"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello from Spring Boot"));
    }

    @Test
    void shouldReturnPersonalizedHelloWhenNameProvided() throws Exception {
        given(helloService.getHelloForName("Jose")).willReturn(new HelloResponse("Hello, Jose"));

        mockMvc.perform(get("/api/hello").param("name", "Jose"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello, Jose"));
    }

    @Test
    void shouldReturnBadRequestWhenNameBlank() throws Exception {
        mockMvc.perform(get("/api/hello").param("name", ""))
                .andExpect(status().isBadRequest());
    }
}
