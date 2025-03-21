package com.app.controller;

import com.app.model.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Save Driver
    @PostMapping("/save")
    public ResponseEntity<Object> saveDriver(
            @RequestParam String driverName,
            @RequestParam String vehicleNo,
            @RequestParam String address,
            @RequestParam double ratePer100Km,
            @RequestParam int maxCapacity,
            @RequestParam int drivingExperience,
            @RequestParam String phoneNo,
            @RequestParam double rating,
            @RequestParam MultipartFile driverPhoto) {

        try {
            // Check for duplicate entries based on unique fields (e.g., vehicleNo or
            // phoneNo)
            String checkSql = "SELECT COUNT(*) FROM driver WHERE vehicleNo = ? OR phoneNo = ?";
            int count = jdbcTemplate.queryForObject(checkSql, Integer.class, new Object[] { vehicleNo, phoneNo });

            if (count > 0) {
                return ResponseEntity.ok(Map.of(
                        "error", true,
                        "message", "Driver with the same vehicle number or phone number already exists."));
            }

            // Insert the new driver record
            String sql = "INSERT INTO driver (driverName, vehicleNo, address, ratePer100Km, maxCapacity, drivingExperience, phoneNo, rating, driverPhoto) "
                    +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            int result = jdbcTemplate.update(sql, driverName, vehicleNo, address, ratePer100Km, maxCapacity,
                    drivingExperience, phoneNo, rating, driverPhoto.getBytes());

            if (result > 0) {
                return ResponseEntity.ok(Map.of(
                        "error", false,
                        "message", "Driver saved successfully!"));
            } else {
                return ResponseEntity.ok(Map.of(
                        "error", true,
                        "message", "Failed to save driver!"));
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the error for debugging
            return ResponseEntity.ok(Map.of(
                    "error", true,
                    "message", "An error occurred while saving the driver. Please try again!"));
        }
    }

    // Get Driver by ID
    @PostMapping("/getDriver")
    public ResponseEntity<Object> getDriver(@RequestBody Map<String, Integer> requestBody) {
        try {
            // Extract the ID from the request body
            Integer driverId = requestBody.get("driverId");
            if (driverId == null) {
                return ResponseEntity.ok(Map.of(
                        "error", true,
                        "message", "Driver ID is required."));
            }

            // Corrected SQL query to use 'driverId'
            String sql = "SELECT * FROM driver WHERE driverId = ?";
            Driver driver = jdbcTemplate.queryForObject(sql, driverRowMapper(), new Object[] { driverId });

            return ResponseEntity.ok(Map.of(
                    "error", false,
                    "data", driver));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.ok(Map.of(
                    "error", true,
                    "message", ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.ok(Map.of(
                    "error", true,
                    "message", "Driver not found or invalid input!"));
        }
    }

    // Fetch all drivers
   @PostMapping("/all")
    public ResponseEntity<List<Driver>> getAllDrivers() {
        try {
            String sql = "SELECT * FROM driver";
            List<Driver> drivers = jdbcTemplate.query(sql, driverRowMapper());
            return ResponseEntity.ok(drivers);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // RowMapper for Driver
    private RowMapper<Driver> driverRowMapper() {
        return (rs, rowNum) -> {
            Driver driver = new Driver();
            driver.setDriverId(rs.getInt("driverId"));
            driver.setDriverName(rs.getString("driverName"));
            driver.setVehicleNo(rs.getString("vehicleNo"));
            driver.setAddress(rs.getString("address"));
            driver.setRatePer100Km(rs.getDouble("ratePer100Km"));
            driver.setMaxCapacity(rs.getInt("maxCapacity"));
            driver.setDrivingExperience(rs.getInt("drivingExperience"));
            driver.setPhoneNo(rs.getString("phoneNo"));
            driver.setRating(rs.getDouble("rating"));
            driver.setDriverPhoto(rs.getBytes("driverPhoto")); // Assuming photo is stored as a BLOB
            return driver;
        };
    }
}
