package com.spring.codebuild.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/errors")
public class ErrorsController {

    @GetMapping("/getError")
    public ResponseEntity errorGetAccess() {
        return ResponseEntity.ok("User is not authorized");
    }

    @PutMapping("/putError")
    public ResponseEntity errorPatchAccess() {
        return ResponseEntity.ok("User is not authorized");
    }

    @DeleteMapping("/deleteError")
    public ResponseEntity errorDeleteAccess() {
        return ResponseEntity.ok("User is not authorized");
    }
}
