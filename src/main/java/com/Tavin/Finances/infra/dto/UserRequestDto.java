package com.Tavin.Finances.infra.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequestDto(
        @NotBlank
                @Size(min = 1, max = 100)
        String name,
        @NotBlank
            @Size(min = 8, max = 100)
        String password,
        @NotBlank
            @Email
        String email) {
}