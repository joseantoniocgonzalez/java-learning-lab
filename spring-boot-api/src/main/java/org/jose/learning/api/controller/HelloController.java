package org.jose.learning.api.controller;

import org.jose.learning.api.dto.HelloResponse;
import org.jose.learning.api.service.HelloService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class HelloController {

    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/api/hello")
    public HelloResponse hello(@RequestParam(required = false) String name) {
        if (name == null) {
            return helloService.getHello();
        }
        if (name.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "name must not be blank");
        }
        return helloService.getHelloForName(name);
    }
}
