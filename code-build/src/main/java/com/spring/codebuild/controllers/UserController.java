package com.spring.codebuild.controllers;

import com.spring.codebuild.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userUpService;

    @Autowired
    public UserController(UserService userUpService) {
        this.userUpService = userUpService;
    }

    @GetMapping("")
    public ResponseEntity showUser(HttpServletRequest request){
        String user = userUpService.getUser(request);

        return ResponseEntity.ok(user);
    }

    @PutMapping("/change")
    public ResponseEntity updateUser(@RequestBody Map<String, Object> user, HttpServletRequest request){
        String changing = userUpService.updateUser(user, request);

        return ResponseEntity.ok(changing);
    }
}
