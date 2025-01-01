package com.app.repository;

import com.app.model.Warehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WarehouseRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Save a warehouse
    public int saveWarehouse(Warehouse warehouse) {
        String sql = "INSERT INTO warehouse (location, maximumCapacity, currentCapacity, placeName, ownerName, ratePerQuintalPurchase, ratePerQuintalSelling, cropsAvailable, quantity) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                warehouse.getLocation(),
                warehouse.getMaximumCapacity(),
                warehouse.getCurrentCapacity(),
                warehouse.getPlaceName(),
                warehouse.getOwnerName(),
                warehouse.getRatePerQuintalPurchase(),
                warehouse.getRatePerQuintalSelling(),
                warehouse.getCropsAvailable(),
                warehouse.getQuantity());
    }

    // Get all warehouses
    public List<Warehouse> getAllWarehouses() {
        String sql = "SELECT * FROM warehouse";
        return jdbcTemplate.query(sql, warehouseRowMapper());
    }

    // Get a warehouse by ID
    @SuppressWarnings("deprecation")
    public Warehouse getWarehouseById(int id) {
        String sql = "SELECT * FROM warehouse WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, warehouseRowMapper());
    }

    // RowMapper for Warehouse
    private RowMapper<Warehouse> warehouseRowMapper() {
        return (rs, rowNum) -> {
            Warehouse warehouse = new Warehouse();
            warehouse.setId(rs.getInt("id"));
            warehouse.setLocation(rs.getString("location"));
            warehouse.setMaximumCapacity(rs.getInt("maximumCapacity"));
            warehouse.setCurrentCapacity(rs.getInt("currentCapacity"));
            warehouse.setPlaceName(rs.getString("placeName"));
            warehouse.setOwnerName(rs.getString("ownerName"));
            warehouse.setRatePerQuintalPurchase(rs.getDouble("ratePerQuintalPurchase"));
            warehouse.setRatePerQuintalSelling(rs.getDouble("ratePerQuintalSelling"));
            warehouse.setCropsAvailable(rs.getString("cropsAvailable"));
            warehouse.setQuantity(rs.getInt("quantity"));
            return warehouse;
        };
    }
}
