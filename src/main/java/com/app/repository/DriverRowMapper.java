package com.app.repository;

import com.app.model.Driver;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DriverRowMapper implements RowMapper<Driver> {
    @Override
    public Driver mapRow(ResultSet rs, int rowNum) throws SQLException {
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
        driver.setDriverPhoto(rs.getBytes("driverPhoto"));
        return driver;
    }
}
