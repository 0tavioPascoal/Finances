package com.Tavin.Finances.infra.dto.releasesdto;

import com.Tavin.Finances.entities.enuns.TypeReleases;
import jakarta.validation.constraints.NotNull;

public record ReleasesTypeDto(
        @NotNull
        TypeReleases type) {
}
