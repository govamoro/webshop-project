package com.radnoti.webshop.service;


import com.radnoti.webshop.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {


    private final JwtUtil jwtUtil;

    public boolean validate(String authHeader) {
        return jwtUtil.validateJwt(authHeader);

    }
}
