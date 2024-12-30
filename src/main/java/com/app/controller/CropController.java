package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.dto.CropRequest;
import com.app.model.Crop;
import com.app.model.CropDetailsResponse;
import com.app.service.CropService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/crops")
public class CropController {

    @Autowired
    private CropService cropService;

    @PostMapping("/add")
    public ResponseEntity<?> addCrop(@RequestBody Crop crop) {
        try {
            cropService.addCrop(crop);
            return ResponseEntity.ok(Map.of(
                "status", "success",
                "message", "Crop added successfully"
            ));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.ok(Map.of(
                "status", "error",
                "message", ex.getMessage()
            ));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.ok(Map.of(
                "status", "error",
                "message", "An error occurred while adding the crop."
            ));
        }
    }

    @PostMapping("/get")
    public ResponseEntity<?> getCrops(@RequestBody Map<String, Long> requestBody) {
        try {
            Long farmerId = requestBody.get("farmerId");
            if (farmerId == null) {
                throw new IllegalArgumentException("Farmer ID is required.");
            }
            List<Crop> crops = cropService.getCrops(farmerId);
            return ResponseEntity.ok(Map.of(
                "status", crops.isEmpty() ? "success" : "error",
                "message", crops.isEmpty() ? "No crops found for the given farmer." : "Crops retrieved successfully.",
                "data", crops
            ));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.ok(Map.of(
                "status", "error",
                "message", ex.getMessage()
            ));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.ok(Map.of(
                "status", "error",
                "message", "An error occurred while retrieving crops."
            ));
        }
    }

    @PostMapping("/getById")
    public ResponseEntity<?> getCropById(@RequestBody Map<String, Long> requestBody) {
        try {
            Long cropId = requestBody.get("cropId");
            if (cropId == null) {
                throw new IllegalArgumentException("Crop ID is required.");
            }
            Crop crop = cropService.getCropById(cropId);
            return ResponseEntity.ok(Map.of(
                "status", crop != null ? "success" : "error",
                "message", crop != null ? "Crop retrieved successfully." : "Crop not found.",
                "data", crop
            ));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.ok(Map.of(
                "status", "error",
                "message", ex.getMessage()
            ));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.ok(Map.of(
                "status", "error",
                "message", "An error occurred while retrieving the crop."
            ));
        }
    }

    @PostMapping("/getAll")
    public ResponseEntity<?> getAllCrops(@RequestBody Map<String, Long> requestBody) {
        try {
            Long farmerId = requestBody.get("farmerId");
            if (farmerId == null) {
                throw new IllegalArgumentException("Farmer ID is required.");
            }
            List<Crop> crops = cropService.getCrops(farmerId);
            return ResponseEntity.ok(Map.of(
                "status", crops.isEmpty() ? "success" : "error",
                "message", crops.isEmpty() ? "No crops found for the given farmer." : "Crops retrieved successfully.",
                "data", crops
            ));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.ok(Map.of(
                "status", "error",
                "message", ex.getMessage()
            ));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.ok(Map.of(
                "status", "error",
                "message", "An error occurred while retrieving crops."
            ));
        }
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateCrop(@RequestBody Map<String, Object> requestBody) {
        try {
            Long cropId = Long.valueOf(requestBody.get("cropId").toString());
            if (cropId == null) {
                throw new IllegalArgumentException("Crop ID is required.");
            }
            Crop cropDetails = new ObjectMapper().convertValue(requestBody.get("cropDetails"), Crop.class);
            cropService.updateCrop(cropId, cropDetails);
            return ResponseEntity.ok(Map.of(
                "status", "success",
                "message", "Crop updated successfully"
            ));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.ok(Map.of(
                "status", "error",
                "message", ex.getMessage()
            ));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.ok(Map.of(
                "status", "error",
                "message", "An error occurred while updating the crop."
            ));
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteCrop(@RequestBody Map<String, Long> requestBody) {
        try {
            Long cropId = requestBody.get("cropId");
            if (cropId == null) {
                throw new IllegalArgumentException("Crop ID is required.");
            }
            cropService.deleteCrop(cropId);
            return ResponseEntity.ok(Map.of(
                "status", "success",
                "message", "Crop deleted successfully"
            ));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.ok(Map.of(
                "status", "error",
                "message", ex.getMessage()
            ));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.ok(Map.of(
                "status", "error",
                "message", "An error occurred while deleting the crop."
            ));
        }
    }

    @PostMapping("/details")
    public ResponseEntity<?> getCropDetails(@RequestBody CropRequest request) {
        try {
            List<CropDetailsResponse> cropDetails = cropService.getCropDetails(request.getCropCode());
            return ResponseEntity.ok(Map.of(
                "status", cropDetails.isEmpty() ? "success" : "error",
                "message", cropDetails.isEmpty() ? "No crop details found." : "Crop details retrieved successfully.",
                "data", cropDetails
            ));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.ok(Map.of(
                "status", "error",
                "message", "An error occurred while retrieving crop details."
            ));
        }
    }
}
