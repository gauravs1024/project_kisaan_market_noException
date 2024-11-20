package com.app.controller;

import com.app.dto.CropRequest;
import com.app.model.UserDtls;
import com.app.service.UserService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(@RequestBody UserDtls user) {
        userService.registerUser(user);
        return "User registered successfully!";
    }

    @PostMapping("/login")
    public String login(@RequestBody UserDtls userDtls) {
        UserDtls user = userService.login(userDtls.getPhoneNumber(), userDtls.getPassword());
        if (user != null) {
            return "Login successful! Welcome, " + user.getName();
        }
        return "Invalid phone number or password.";
    }

    @PostMapping("/farmers")
    public ResponseEntity<List<String>> getFarmerNamesByCropCode(@RequestBody CropRequest cropRequest) {
        try {
            List<String> farmerNames = userService.getFarmerNamesByCropCode(cropRequest.getCropCode());
            if (farmerNames.isEmpty()) {
                return ResponseEntity.noContent().build(); // 204 No Content
            }
            return ResponseEntity.ok(farmerNames); // 200 OK
        } catch (Exception e) {
            e.printStackTrace(); // Log the error
            return ResponseEntity.status(500).build(); 
        }}

}
