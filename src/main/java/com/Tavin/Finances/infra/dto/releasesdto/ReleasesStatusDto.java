package com.Tavin.Finances.infra.dto.releasesdto;

import com.Tavin.Finances.entities.enuns.StatusReleases;

import jakarta.validation.constraints.NotNull;

public record ReleasesStatusDto(
        @NotNull
        StatusReleases status) {
}
