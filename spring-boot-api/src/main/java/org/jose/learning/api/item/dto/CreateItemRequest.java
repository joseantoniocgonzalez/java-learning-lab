package org.jose.learning.api.item.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateItemRequest(@NotBlank String name) {}
