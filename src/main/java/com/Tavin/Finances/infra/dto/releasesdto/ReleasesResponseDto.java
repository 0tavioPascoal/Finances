package com.Tavin.Finances.infra.dto.releasesdto;

import com.Tavin.Finances.entities.enuns.StatusReleases;
import com.Tavin.Finances.entities.enuns.TypeReleases;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.UUID;

public record ReleasesResponseDto(
                                  String description,


                                  BigDecimal amount,

                                  Integer day,

                                  Integer month,

                                  Integer year,

                                  UUID idUser,

                                  TypeReleases type,

                                  StatusReleases status)  {
}
