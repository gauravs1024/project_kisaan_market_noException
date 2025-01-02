package com.app.controller;

import com.app.model.Warehouse;
import com.app.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            return ResponseEntity.ok(warehouse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Warehouse not found!");
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
