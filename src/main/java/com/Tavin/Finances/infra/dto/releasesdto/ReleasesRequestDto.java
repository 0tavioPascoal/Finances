package com.Tavin.Finances.infra.dto.releasesdto;

import com.Tavin.Finances.entities.enuns.StatusReleases;
import com.Tavin.Finances.entities.enuns.TypeReleases;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.UUID;

public record ReleasesRequestDto(

        @NotBlank
                @Size(min = 1, max = 100)
        String description,

        @NotNull @DecimalMin(value = "0.00", inclusive = true)
        BigDecimal  amount,
        @NotNull
        Integer day,
        @NotNull
        Integer month,
        @NotNull
        Integer year,
        @NotNull
        UUID idUser,
        @NotNull
        TypeReleases type,
        @NotNull
        StatusReleases status) {
}
