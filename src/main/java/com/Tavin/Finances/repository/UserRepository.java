package com.Tavin.Finances.repository;

import com.Tavin.Finances.entities.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {

    Optional<UserModel> findById(UUID id);

    Optional<UserModel> findByName(String name);

    UserDetails findByLogin(String login);
}
