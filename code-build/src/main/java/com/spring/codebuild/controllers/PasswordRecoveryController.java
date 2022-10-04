package com.spring.codebuild.controllers;

import com.spring.codebuild.Services.PasswordRecoveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/recovery")
public class PasswordRecoveryController {
    private PasswordRecoveryService passwordRecoveryService;

    @Autowired
    public PasswordRecoveryController(PasswordRecoveryService passwordRecoveryService){
        this.passwordRecoveryService=passwordRecoveryService;
    }

    @PostMapping("")
    public ResponseEntity passwordRecovery(@RequestBody String email){
        String message = passwordRecoveryService.passwordRecovery(email);

        return ResponseEntity.ok(message);
    }
}
