package com.app.controller;

import com.app.dto.BuyerDto;
import com.app.dto.CropRequest;
import com.app.model.FarmerRegisDetails;
import com.app.model.UserDtls;
import com.app.service.UserService;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> register(@RequestBody UserDtls user) {
        Map<String, Object> response = userService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // @GetMapping("/csrf-token")
    // public CsrfToken getCsrfToken(HttpServletRequest request){
    // return (CsrfToken) request.getAttribute("_csrf");
    // }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDtls userDtls) {
        Map<String, Object> user = userService.login(userDtls.getPhoneNumber(), userDtls.getPassword());
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid phone number or password.");
    }

    @PostMapping("/farmer-register")
    public String farmerRegi(@RequestBody FarmerRegisDetails farmer) {
        userService.farmerRegister(farmer);
        return "Farmer registered successfully!";
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
        }
    }

    @PostMapping("/buyers")
    public List<BuyerDto> getAllBuyers() {
        return userService.getAllBuyers();
    }
}
