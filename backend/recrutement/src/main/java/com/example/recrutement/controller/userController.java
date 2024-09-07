package com.example.recrutement.controller;
import com.example.recrutement.dto.*;
import com.example.recrutement.response.loginResponse;
import com.example.recrutement.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/api/v1/")
@AllArgsConstructor
public class userController {

    @Autowired
    private UserService userService;
    @PostMapping("/auth/login")
    public ResponseEntity<?> loginUser(@RequestBody loginDTO loginDTO) {
        loginResponse loginResponse = userService.loginUser(loginDTO);
        return ResponseEntity.ok(loginResponse);
    }

}
