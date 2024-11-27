package com.app.repository;

import com.app.model.Crop;
import com.app.model.FarmerRegisDetails;
import com.app.model.UserDtls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FarmerRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Fetch Farmer Name
    @SuppressWarnings("deprecation")
    public UserDtls getFarmerById(int farmerId) {
        String sql = "SELECT * FROM users WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{farmerId}, (rs, rowNum) -> {
            UserDtls user = new UserDtls();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setPhoneNumber(rs.getString("phoneNumber"));
            user.setRole(rs.getString("role"));
            user.setPassword(rs.getString("password"));
            return user;
        });
    }

    // Fetch Farmer Registration Details
    @SuppressWarnings("deprecation")
    public FarmerRegisDetails getFarmerRegistrationDetails(int farmerId) {
        String sql = "SELECT * FROM farmer_details WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{farmerId}, (rs, rowNum) -> {
            FarmerRegisDetails details = new FarmerRegisDetails();
            details.setId(rs.getInt("id"));
            details.setFarmerType(rs.getString("farmerType"));
            details.setState(rs.getString("state"));
            details.setCity(rs.getString("city"));
            details.setArea(rs.getString("area"));
            details.setPincode(rs.getString("pincode"));
            return details;
        });
    }

    // Fetch Farmer Crops
    @SuppressWarnings("deprecation")
    public List<Crop> getCropsByFarmerId(int farmer_id) {
        String sql = "SELECT * FROM crops WHERE farmer_id = ?";
        return jdbcTemplate.query(sql, new Object[]{farmer_id}, (rs, rowNum) -> {
            Crop crop = new Crop();
            crop.setId(rs.getLong("id"));
            crop.setCropCode(rs.getString("cropCode"));
            crop.setName(rs.getString("name"));
            crop.setType(rs.getString("type"));
            crop.setPrice(rs.getInt("price"));
            crop.setQuantity(rs.getInt("quantity"));
            crop.setFarmerId(rs.getLong("farmer_id"));
            return crop;
        });
    }
}
