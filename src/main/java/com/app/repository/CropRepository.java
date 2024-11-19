package com.app.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.app.model.Crop;

import java.util.List;

@Repository
public class CropRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int addCrop(Crop crop) {
        String sql = "INSERT INTO crops (name, type, quantity, farmer_id) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, crop.getName(), crop.getType(), crop.getQuantity(), crop.getFarmerId());
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
            return crop;
        });
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
            return crop;
        });
    }

    public int updateCrop(Long id, Crop crop) {
        String sql = "UPDATE crops SET name = ?, type = ?, quantity = ?, farmer_id = ? WHERE id = ?";
        return jdbcTemplate.update(sql, crop.getName(), crop.getType(), crop.getQuantity(), crop.getFarmerId(), id);
    }

    public int deleteCrop(Long id) {
        String sql = "DELETE FROM crops WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
