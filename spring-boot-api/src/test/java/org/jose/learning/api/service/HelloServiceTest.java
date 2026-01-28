package org.jose.learning.api.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HelloServiceTest {

    @Test
    void shouldReturnMessage() {
        HelloService service = new HelloService();
        assertEquals("Hello from Spring Boot", service.getMessage());
    }
}
