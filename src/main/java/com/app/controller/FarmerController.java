package com.app.controller;

import com.app.service.FarmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/farmers")
public class FarmerController {

    @Autowired
    private FarmerService farmerService;

    // Change this to accept the request body
    @PostMapping("/details")
    public ResponseEntity<Map<String, Object>> getFarmerDetails(@RequestBody Map<String, Integer> requestBody) {
        Integer farmerId = requestBody.get("farmerId");
        if (farmerId != null) {
            Map<String, Object> response = farmerService.getFarmerDetails(farmerId);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(Map.of("message", "farmerId is required"));
        }
    }
}
