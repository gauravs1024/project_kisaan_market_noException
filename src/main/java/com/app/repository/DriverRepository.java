package com.app.repository;

import com.app.model.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class DriverRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Save Driver
    public int saveDriver(Driver driver) {
        String sql = "INSERT INTO driver (driverName, vehicleNo, address, ratePer100Km, maxCapacity, drivingExperience, phoneNo, rating, driverPhoto) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                driver.getDriverName(),
                driver.getVehicleNo(),
                driver.getAddress(),
                driver.getRatePer100Km(),
                driver.getMaxCapacity(),
                driver.getDrivingExperience(),
                driver.getPhoneNo(),
                driver.getRating(),
                driver.getDriverPhoto());
    }

    public Optional<Driver> findById(int driverId) {
        String sql = "SELECT * FROM driver WHERE driverId = ?";
        try {
            @SuppressWarnings("deprecation")
            Driver driver = jdbcTemplate.queryForObject(sql, new Object[]{driverId}, new DriverRowMapper());
            return Optional.ofNullable(driver);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    
    public List<Driver> findAll() {
        String sql = "SELECT * FROM driver";
        return jdbcTemplate.query(sql, new DriverRowMapper());
    }

    
    public int deleteById(int driverId) {
        String sql = "DELETE FROM driver WHERE driverId = ?";
        return jdbcTemplate.update(sql, driverId);
    }
}
