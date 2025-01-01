package com.app.controller;

import com.app.model.Warehouse;
import com.app.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    // Save Warehouse
    @PostMapping("/save")
    public ResponseEntity<String> saveWarehouse(@RequestBody Warehouse warehouse) {
        int result = warehouseService.saveWarehouse(warehouse);
        if (result > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Warehouse saved successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save warehouse!");
        }
    }

    // Get all Warehouses (using POST)
    @PostMapping("/all")
    public ResponseEntity<List<Warehouse>> getAllWarehouses() {
        List<Warehouse> warehouses = warehouseService.getAllWarehouses();
        return ResponseEntity.ok(warehouses);
    }

    // Get Warehouse by ID (using POST with request body)
    @PostMapping("/getById")
    public ResponseEntity<Object> getWarehouseById(@RequestBody IdRequest idRequest) {
        try {
            Warehouse warehouse = warehouseService.getWarehouseById(idRequest.getId());

            if (warehouse == null) {
                return ResponseEntity.ok(Map.of(
                        "error", true,
                        "message", "Warehouse not found."));
            }

            return ResponseEntity.ok(Map.of(
                    "error", false,
                    "data", warehouse));
        } catch (Exception e) {
            e.printStackTrace(); // Log the error for debugging
            return ResponseEntity.ok(Map.of(
                    "error", true,
                    "message", "An error occurred while retrieving the warehouse."));
        }
    }

    // Inner class for ID request payload
    public static class IdRequest {
        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
