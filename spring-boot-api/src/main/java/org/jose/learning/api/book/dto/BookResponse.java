package org.jose.learning.api.book.dto;

public record BookResponse(
        Long id,
        String title,
        String author,
        Integer year
) {}
