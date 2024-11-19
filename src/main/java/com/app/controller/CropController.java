package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.model.Crop;
import com.app.service.CropService;

import java.util.List;

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

    
    @GetMapping("/get")
    public ResponseEntity<List<Crop>> getCrops(@RequestParam Long farmerId) {
        List<Crop> crops = cropService.getCrops(farmerId);
        return ResponseEntity.ok(crops);
    }

    
    @GetMapping("/{cropId}")
    public ResponseEntity<Crop> getCropById(@PathVariable Long cropId) {
        Crop crop = cropService.getCropById(cropId);
        return crop != null ? ResponseEntity.ok(crop) : ResponseEntity.notFound().build();
    }
    
    @GetMapping("/getAll")
    public ResponseEntity<List<Crop>> getAllCrops(@RequestParam Long farmerId) {
        List<Crop> crops = cropService.getCrops(farmerId);
        return ResponseEntity.ok(crops);
    }


    
    @PostMapping("/update/{cropId}")
    public ResponseEntity<String> updateCrop(@PathVariable Long cropId, @RequestBody Crop cropDetails) {
        cropService.updateCrop(cropId, cropDetails);
        return ResponseEntity.ok("Crop updated successfully");
    }

    
    @DeleteMapping("/delete/{cropId}")
    public ResponseEntity<String> deleteCrop(@PathVariable Long cropId) {
        cropService.deleteCrop(cropId);
        return ResponseEntity.ok("Crop deleted successfully");
    }
}

