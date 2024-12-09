package com.Tavin.Finances.services;

import com.Tavin.Finances.entities.UserModel;
import com.Tavin.Finances.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;


import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServices {

    private final UserRepository userRepository;

    public void save(UserModel user) {
        userRepository.save(user);
    }

    public Optional<UserModel> findById(UUID id) {
        return userRepository.findById(id);
    }

    public void delete(UserModel user) {
        userRepository.delete(user);
    }

    public void update(UserModel user) {
         userRepository.save(user);
    }



}


