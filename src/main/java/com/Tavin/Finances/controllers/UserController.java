package com.Tavin.Finances.controllers;

import com.Tavin.Finances.entities.UserModel;
import com.Tavin.Finances.infra.dto.usersdto.UserRequestDto;
import com.Tavin.Finances.infra.dto.usersdto.UserResponseDto;
import com.Tavin.Finances.infra.header.GeneratedHeader;
import com.Tavin.Finances.infra.mappers.user.UserMapper;
import com.Tavin.Finances.services.user.UserServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController implements GeneratedHeader {

    private final UserServices service;

    private final UserMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUser(
            @RequestParam String id) {

        return service.findById(UUID.fromString(id))
                .map(UserModel -> {
                    UserResponseDto dto = mapper.UserResponseMapper(UserModel);
                    return ResponseEntity.ok().body(dto);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping()
    public ResponseEntity<Object> deleteUser(
            @RequestParam String id) {

        return service.findById(UUID.fromString(id))
                .map(UserModel -> {
                    service.delete(UserModel);
                    return ResponseEntity.noContent().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping()
    public ResponseEntity<Object> updateUser(
            @RequestParam String id, @RequestBody @Valid UserRequestDto requestDto) {
        return service.findById(UUID.fromString(id))
                .map(UserModel -> {
                    UserModel userAux = mapper.UserModelMapper(requestDto);
                    UserModel.setPassword(userAux.getPassword());
                    UserModel.setName(userAux.getName());
                    UserModel.setEmail(userAux.getEmail());
                    service.update(UserModel);
                    return ResponseEntity.ok().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping()
    public ResponseEntity<Page<UserResponseDto>> teste(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {

        Page<UserModel> list = service.findAll(name, page, pageSize);

        Page<UserResponseDto> result = list.map(UserModel -> mapper.UserResponseMapper(UserModel));
        return ResponseEntity.ok(result);

    }

}
