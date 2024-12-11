package com.Tavin.Finances.entities;

import com.Tavin.Finances.entities.enuns.StatusReleases;
import com.Tavin.Finances.entities.enuns.TypeReleases;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@Table(name = "releases_tb")
@EntityListeners(AuditingEntityListener.class)
public class ReleasesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private String description;

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal amount;

    @Column
    private Integer day;

    @Column
    private Integer month;

    @Column
    private Integer year;

    @CreatedDate
    private LocalDate createdDate;

    @Enumerated(value = EnumType.STRING)
    private TypeReleases type;

    @Enumerated(value = EnumType.STRING)
    private StatusReleases status;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserModel user;


}
