package org.jose.learning.api.service;

import org.jose.learning.api.dto.HelloResponse;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public HelloResponse getHello() {
        return new HelloResponse("Hello from Spring Boot");
    }
}
