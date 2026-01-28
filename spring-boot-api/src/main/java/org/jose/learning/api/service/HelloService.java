package org.jose.learning.api.service;

import org.jose.learning.api.dto.HelloResponse;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public HelloResponse getHello() {
        return new HelloResponse("Hello from Spring Boot");
    }

    public HelloResponse getHelloForName(String name) {
        return new HelloResponse("Hello, " + name);
    }
}
