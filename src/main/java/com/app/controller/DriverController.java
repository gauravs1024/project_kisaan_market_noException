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

@RestController
@RequestMapping("/api/drivers")
public class DriverController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Save Driver
    @PostMapping("/save")
    public ResponseEntity<String> saveDriver(
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
            String sql = "INSERT INTO driver (driverName, vehicleNo, address, ratePer100Km, maxCapacity, drivingExperience, phoneNo, rating, driverPhoto) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            int result = jdbcTemplate.update(sql, driverName, vehicleNo, address, ratePer100Km, maxCapacity, drivingExperience, phoneNo, rating, driverPhoto.getBytes());

            if (result > 0) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Driver saved successfully!");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save driver!");
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving driver!");
        }
    }

    // Get Driver by ID
    @PostMapping("/{id}")
    public ResponseEntity<Object> getDriver(@PathVariable int id) {
        try {
            String sql = "SELECT * FROM driver WHERE id = ?";
            @SuppressWarnings("deprecation")
            Driver driver = jdbcTemplate.queryForObject(sql, new Object[]{id}, driverRowMapper());

            return ResponseEntity.ok(driver);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Driver not found!");
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
            driver.setDriverId(rs.getInt("driverId")); // Ensure column is `id`
            driver.setDriverName(rs.getString("driverName")); // Camel case
            driver.setVehicleNo(rs.getString("vehicleNo")); // Camel case
            driver.setAddress(rs.getString("address")); // Camel case
            driver.setRatePer100Km(rs.getDouble("ratePer100Km")); // Camel case
            driver.setMaxCapacity(rs.getInt("maxCapacity")); // Camel case
            driver.setDrivingExperience(rs.getInt("drivingExperience")); // Camel case
            driver.setPhoneNo(rs.getString("phoneNo")); // Camel case
            driver.setRating(rs.getDouble("rating")); // Camel case
            driver.setDriverPhoto(rs.getBytes("driverPhoto")); // Camel case
            return driver;
        };
    }
}
