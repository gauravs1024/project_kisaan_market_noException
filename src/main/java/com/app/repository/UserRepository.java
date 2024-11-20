package com.app.repository;

import com.app.model.UserDtls;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void save(UserDtls user) {
        String sql = "INSERT INTO users (name, phoneNumber, password) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, user.getName(), user.getPhoneNumber(), user.getPassword());
    }

    @SuppressWarnings("deprecation")
    public UserDtls findByPhoneNumber(String phoneNumber) {
        String sql = "SELECT * FROM users WHERE phoneNumber = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{phoneNumber}, (rs, rowNum) -> {
            UserDtls user = new UserDtls();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setPhoneNumber(rs.getString("phoneNumber"));
            user.setPassword(rs.getString("password"));
            return user;
        });
    }
}
