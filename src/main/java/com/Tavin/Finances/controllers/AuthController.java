package com.Tavin.Finances.controllers;

import com.Tavin.Finances.entities.UserModel;
import com.Tavin.Finances.infra.dto.usersdto.auth.AuthenticationDto;
import com.Tavin.Finances.infra.dto.usersdto.token.TokenDto;
import com.Tavin.Finances.infra.dto.usersdto.UserRequestDto;
import com.Tavin.Finances.infra.header.GeneratedHeader;
import com.Tavin.Finances.infra.mappers.user.UserMapper;
import com.Tavin.Finances.services.token.TokenService;
import com.Tavin.Finances.services.user.UserServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController implements GeneratedHeader {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserMapper mapper;

    @Autowired
    private UserServices userServices;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> Login(@RequestBody @Valid AuthenticationDto dto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.login(), dto.password());
        var auth = authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generatedToken((UserModel) auth.getPrincipal());

        return ResponseEntity.ok(new TokenDto(token));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> save(@RequestBody @Valid UserRequestDto requestDto) {
        var dto = mapper.UserModelMapper(requestDto);
        userServices.save(dto);
        var location = genertatedURI(dto.getId());

        return ResponseEntity.created(location).build();
    }
}
