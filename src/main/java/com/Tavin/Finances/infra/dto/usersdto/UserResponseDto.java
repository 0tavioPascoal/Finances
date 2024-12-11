package com.Tavin.Finances.infra.dto.usersdto;

import java.util.UUID;

public record UserResponseDto(UUID id,
                              String name,
                              String email) {
}
