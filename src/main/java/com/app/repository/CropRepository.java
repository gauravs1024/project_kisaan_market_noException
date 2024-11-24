package com.app.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.app.model.Crop;

import java.util.List;

@Repository
public class CropRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int addCrop(Crop crop) {
        String sql = "INSERT INTO crops (name, type, quantity, farmer_id, cropCode , price) VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, crop.getName(), crop.getType(), crop.getQuantity(), crop.getFarmerId(),
                crop.getCropCode(), crop.getPrice());
    }

    @SuppressWarnings("deprecation")
    public List<Crop> getCrops(Long farmerId) {
        String sql = "SELECT * FROM crops WHERE farmer_id = ?";
        return jdbcTemplate.query(sql, new Object[] { farmerId }, (rs, rowNum) -> {
            Crop crop = new Crop();
            crop.setId(rs.getLong("id"));
            crop.setName(rs.getString("name"));
            crop.setType(rs.getString("type"));
            crop.setQuantity(rs.getInt("quantity"));
            crop.setFarmerId(rs.getLong("farmer_id"));
            crop.setCropCode(rs.getString("cropCode"));
            crop.setPrice(rs.getInt("price"));
            return crop;
        });
    }

    @SuppressWarnings("deprecation")
    public Crop getCropById(Long id) {
        String sql = "SELECT * FROM crops WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[] { id }, (rs, rowNum) -> {
            Crop crop = new Crop();
            crop.setId(rs.getLong("id"));
            crop.setName(rs.getString("name"));
            crop.setType(rs.getString("type"));
            crop.setQuantity(rs.getInt("quantity"));
            crop.setFarmerId(rs.getLong("farmer_id"));
            crop.setCropCode(rs.getString("cropCode"));
            crop.setPrice(rs.getInt("price"));
            return crop;
        });
    }
    
    private RowMapper<Crop> cropRowMapper() {
        return (rs, rowNum) -> {
            Crop crop = new Crop();
            crop.setId(rs.getLong("id"));
            crop.setCropCode(rs.getString("cropCode"));
            crop.setName(rs.getString("name"));
            crop.setType(rs.getString("type"));
            crop.setPrice(rs.getInt("price"));
            crop.setQuantity(rs.getInt("quantity"));
            crop.setFarmerId(rs.getLong("farmer_id"));
            return crop;
        };
    }

    // Method to fetch crop details by cropCode
    @SuppressWarnings("deprecation")
    public Crop findCropByCode(String cropCode) {
        String sql = "SELECT * FROM crops WHERE cropCode = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{cropCode}, cropRowMapper());
    }

    @SuppressWarnings("deprecation")
    public List<Crop> getCropsByFarmerId(Long farmerId) {
        String sql = "SELECT * FROM crops WHERE farmer_id = ?";
        return jdbcTemplate.query(sql, new Object[] { farmerId }, (rs, rowNum) -> {
            Crop crop = new Crop();
            crop.setId(rs.getLong("id"));
            crop.setName(rs.getString("name"));
            crop.setType(rs.getString("type"));
            crop.setQuantity(rs.getInt("quantity"));
            crop.setFarmerId(rs.getLong("farmer_id"));
            crop.setCropCode(rs.getString("cropCode"));
            crop.setPrice(rs.getInt("price"));
            return crop;
        });
    }

    public int updateCrop(Long id, Crop crop) {
        String sql = "UPDATE crops SET name = ?, type = ?, quantity = ?, farmer_id = ?,cropCode=?,price=? WHERE id = ?";
        return jdbcTemplate.update(sql, crop.getName(), crop.getType(), crop.getQuantity(), crop.getFarmerId(),
                crop.getCropCode(), crop.getPrice(), id);
    }

    public int deleteCrop(Long id) {
        String sql = "DELETE FROM crops WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}