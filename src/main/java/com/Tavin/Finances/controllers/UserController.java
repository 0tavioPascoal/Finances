package com.Tavin.Finances.controllers;

import com.Tavin.Finances.entities.UserModel;
import com.Tavin.Finances.infra.dto.UserRequestDto;
import com.Tavin.Finances.infra.dto.UserResponseDto;
import com.Tavin.Finances.infra.header.GeneratedHeader;
import com.Tavin.Finances.infra.mappers.UserMapper;
import com.Tavin.Finances.services.UserServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController implements GeneratedHeader {

    private final UserServices service;
    private final UserMapper mapper;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid UserRequestDto requestDto) {
        var dto = mapper.UserModelMapper(requestDto);
        service.save(dto);
        var location = genertatedURI(dto.getId());

        return ResponseEntity.created(location).build();
    }

    @GetMapping()
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
            @RequestParam String id){

        return service.findById(UUID.fromString(id))
                .map(UserModel -> {
                    service.delete(UserModel);
                    return ResponseEntity.noContent().build();})
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping()
    public ResponseEntity<Object> updateUser(
            @RequestParam String id, @RequestBody @Valid UserRequestDto requestDto){
        return service.findById(UUID.fromString(id))
                .map(UserModel -> {
                    UserModel userAux = mapper.UserModelMapper(requestDto);
                    UserModel.setPassword(userAux.getPassword());
                    UserModel.setName(userAux.getName());
                    UserModel.setEmail(userAux.getEmail());
                    service.update(UserModel);
                     return ResponseEntity.ok().build();
                }).orElseGet(()-> ResponseEntity.notFound().build());
    }


}
