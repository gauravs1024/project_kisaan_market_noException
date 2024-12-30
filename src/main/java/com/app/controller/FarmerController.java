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

    @PostMapping("/details")
    public ResponseEntity<Map<String, Object>> getFarmerDetails(@RequestBody Map<String, Integer> requestBody) {
        Map<String, Object> response = new java.util.HashMap<>();
        try {
            Integer farmerId = requestBody.get("farmerId");
            if (farmerId == null) {
                response.put("status", "error");
                response.put("message", "farmerId is required");
                return ResponseEntity.ok(response); // Always return 200 OK
            }

            Map<String, Object> farmerDetails = farmerService.getFarmerDetails(farmerId);

            if (farmerDetails == null || farmerDetails.isEmpty()) {
                response.put("status", "error");
                response.put("message", "No details found for the given farmerId.");
            } else {
                response.put("status", "success");
                response.put("data", farmerDetails);
            }
        } catch (Exception ex) {
            ex.printStackTrace(); // Log for debugging
            response.put("status", "error");
            response.put("message", "Farmer Details Not Found");
        }

        return ResponseEntity.ok(response); // Always return 200 OK
    }
}
