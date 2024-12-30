package com.app.controller;

import com.app.dto.BuyerDto;
import com.app.dto.CropRequest;
import com.app.model.FarmerRegisDetails;
import com.app.model.UserDtls;
import com.app.service.UserService;
import java.util.List;
import java.util.Map;
// import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Map<String, Object>> register(@RequestBody UserDtls user) {
        try {
            Map<String, Object> response = userService.registerUser(user);
            return ResponseEntity.ok(Map.of(
                "status", "success",
                "data", response
            ));
        } catch (Exception ex) {
            ex.printStackTrace(); // Log the error
            return ResponseEntity.ok(Map.of(
                "status", "error",
                "message", "An unexpected error occurred while registering the user."
            ));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody UserDtls userDtls) {
        try {
            Map<String, Object> user = userService.login(userDtls.getPhoneNumber(), userDtls.getPassword());
            return ResponseEntity.ok(Map.of(
                "status", "success",
                "data", user
            ));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.ok(Map.of(
                "status", "error",
                "message", ex.getMessage()
            ));
        } catch (Exception ex) {
            ex.printStackTrace(); // Log the error
            return ResponseEntity.ok(Map.of(
                "status", "error",
                "message", "An unexpected error occurred while logging in."
            ));
        }
    }

    @PostMapping("/farmer-register")
    public ResponseEntity<Map<String, Object>> registerFarmer(@RequestBody FarmerRegisDetails farmer) {
        try {
            userService.farmerRegister(farmer);
            return ResponseEntity.ok(Map.of(
                "status", "success",
                "message", "Farmer registered successfully!"
            ));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.ok(Map.of(
                "status", "error",
                "message", ex.getMessage()
            ));
        } catch (Exception ex) {
            ex.printStackTrace(); // Log the error
            return ResponseEntity.ok(Map.of(
                "status", "error",
                "message", "An unexpected error occurred while registering the farmer."
            ));
        }
    }

    @PostMapping("/farmers")
    public ResponseEntity<Map<String, Object>> getFarmerNamesByCropCode(@RequestBody CropRequest cropRequest) {
        try {
            List<String> farmerNames = userService.getFarmerNamesByCropCode(cropRequest.getCropCode());
            if (farmerNames.isEmpty()) {
                return ResponseEntity.ok(Map.of(
                    "status", "success",
                    "message", "No farmers found for the given crop code."
                ));
            }
            return ResponseEntity.ok(Map.of(
                "status", "success",
                "data", farmerNames
            ));
        } catch (Exception ex) {
            ex.printStackTrace(); // Log the error
            return ResponseEntity.ok(Map.of(
                "status", "error",
                "message", "An unexpected error occurred while fetching farmer names."
            ));
        }
    }

    @PostMapping("/buyers")
    public ResponseEntity<Map<String, Object>> getAllBuyers() {
        try {
            List<BuyerDto> buyers = userService.getAllBuyers();
            return ResponseEntity.ok(Map.of(
                "status", "success",
                "data", buyers
            ));
        } catch (Exception ex) {
            ex.printStackTrace(); // Log the error
            return ResponseEntity.ok(Map.of(
                "status", "error",
                "message", "An unexpected error occurred while fetching buyer details."
            ));
        }
    }
}
