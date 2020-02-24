package com.generali.jwtauthbackend.controller;

import com.generali.jwtauthbackend.payload.JsonWebTokenResponse;
import com.generali.jwtauthbackend.payload.RequestLogin;
import com.generali.jwtauthbackend.payload.RequestRegister;
import com.generali.jwtauthbackend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody @Valid RequestRegister requestRegister){
        authService.register(requestRegister);
        return ResponseEntity.ok("success");
    }

    @PostMapping(value = "/login")
    public ResponseEntity<JsonWebTokenResponse> login(@RequestBody @Valid RequestLogin requestLogin){
        JsonWebTokenResponse jsonWebTokenResponse = authService.login(requestLogin);
        return ResponseEntity.ok(jsonWebTokenResponse);
    }
}
