package com.radnoti.webshop.controller;

import com.radnoti.webshop.model.dto.ResponseDto;
import com.radnoti.webshop.model.dto.UserDto;
import com.radnoti.webshop.service.AuthService;
import com.radnoti.webshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping(path = "/registration")
    public ResponseDto registration(@RequestBody UserDto userDto) throws NoSuchAlgorithmException {
        return userService.registration(userDto);
    }

    @PostMapping(path = "/login")
    public ResponseDto login(@RequestBody UserDto userDto) throws NoSuchAlgorithmException {
        return userService.login(userDto);
    }

    // oldal betolteseert felel
    @GetMapping(path = "/validate")
    public ResponseEntity<Boolean> validate(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        return ResponseEntity.ok(authService.validate(authHeader));
    }
}
