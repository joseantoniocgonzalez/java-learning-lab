package org.jose.learning.api.controller;

import org.jose.learning.api.dto.ErrorResponse;
import org.jose.learning.api.dto.HelloResponse;
import org.jose.learning.api.service.HelloService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/api/hello")
    public ResponseEntity<?> hello(@RequestParam(required = false) String name) {
        if (name == null) {
            return ResponseEntity.ok(helloService.getHello());
        }
        if (name.isBlank()) {
            return ResponseEntity.badRequest().body(new ErrorResponse("name must not be blank"));
        }
        return ResponseEntity.ok(helloService.getHelloForName(name));
    }
}
