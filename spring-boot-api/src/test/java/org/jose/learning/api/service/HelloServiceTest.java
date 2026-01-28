package org.jose.learning.api.service;

import org.jose.learning.api.dto.HelloResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HelloServiceTest {

    @Test
    void shouldReturnHelloResponse() {
        HelloService service = new HelloService();
        HelloResponse response = service.getHello();
        assertEquals("Hello from Spring Boot", response.message());
    }
}
