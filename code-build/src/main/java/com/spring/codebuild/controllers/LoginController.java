package com.spring.codebuild.controllers;

import com.spring.codebuild.DAO.UserDAO;
import com.spring.codebuild.Services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final LoginService loginService;
    private final UserDAO userDAO;

    @Autowired
    public LoginController(LoginService loginService, UserDAO userDAO){
        this.loginService=loginService;
        this.userDAO=userDAO;
    }
    @PostMapping("")
    public ResponseEntity login(@RequestBody Map<String, Object> values){
        String message = loginService.authorization(values);

        return ResponseEntity.ok(message);
    }

    @GetMapping("/ind")
    public ResponseEntity index(){
        return ResponseEntity.ok(userDAO.index());
    }
}
