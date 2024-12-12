package com.Tavin.Finances.services.user;

import com.Tavin.Finances.entities.UserModel;
import com.Tavin.Finances.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServices {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public void save(UserModel user) {
        var senha = user.getPassword();
        user.setPassword(passwordEncoder.encode(senha));
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

    public Page<UserModel> findAll(String name, Integer page, Integer pageSize) {
        UserModel user = new UserModel();
        user.setName(name);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase()
                .withIgnoreNullValues();
        Example<UserModel> example = Example.of(user, matcher);

        Pageable pageable = PageRequest.of(page, pageSize);
        return userRepository.findAll(example, pageable);



    }

}


