package com.Tavin.Finances.repository;

import com.Tavin.Finances.entities.ReleasesModel;
import com.Tavin.Finances.entities.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReleasesRepository extends JpaRepository<ReleasesModel, UUID>, JpaSpecificationExecutor<UserModel> {

    boolean existsByUser(UserModel user);

    Optional<ReleasesModel> findByAmountAndDescriptionAndDayAndYearAndMonth(BigDecimal amount, String description, Integer day, Integer year, Integer month);

}
