package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.model.Crop;
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
    public ResponseEntity<String> addCrop(@RequestBody Crop crop) {
        cropService.addCrop(crop);
        return ResponseEntity.ok("Crop added successfully");
    }

    @PostMapping("/get")
    public ResponseEntity<List<Crop>> getCrops(@RequestBody Map<String, Long> requestBody) {
        Long farmerId = requestBody.get("farmerId");
        List<Crop> crops = cropService.getCrops(farmerId);
        return ResponseEntity.ok(crops);
    }

    @PostMapping("/getById")
    public ResponseEntity<Crop> getCropById(@RequestBody Map<String, Long> requestbody) {
        Long cropId = requestbody.get("cropId");
        Crop crop = cropService.getCropById(cropId);
        return crop != null ? ResponseEntity.ok(crop) : ResponseEntity.notFound().build();
    }

    @PostMapping("/getAll")
    public ResponseEntity<List<Crop>> getAllCrops(@RequestBody Map<String, Long> requestBody) {
        Long farmerId = requestBody.get("farmerId");
        List<Crop> crops = cropService.getCrops(farmerId);
        return ResponseEntity.ok(crops);
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateCrop(@RequestBody Map<String, Object> requestbody) {
        Long cropId = Long.valueOf(requestbody.get("cropId").toString());
        Crop cropDetails = new ObjectMapper().convertValue(requestbody.get("cropDetails"), Crop.class);

        cropService.updateCrop(cropId, cropDetails);
        return ResponseEntity.ok("Crop updated successfully");
    }

    @DeleteMapping("/delete/{cropId}")
    public ResponseEntity<String> deleteCrop(@PathVariable Long cropId) {
        cropService.deleteCrop(cropId);
        return ResponseEntity.ok("Crop deleted successfully");
    }
}