package com.app.service;

import com.app.ExceptionHandlefile.ResourceNotFoundException;
import com.app.dto.BuyerDto;
import com.app.model.FarmerRegisDetails;
import com.app.model.UserDtls;
import com.app.repository.UserRepository;
import com.app.security.JwtUtil;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LinkedHashMap<String, Object> registerUser(UserDtls user) {
        userRepository.save(user);
        UserDtls savedUser = userRepository.findByPhoneNumber(user.getPhoneNumber());
        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        response.put("userId", savedUser.getId());
        response.put("name", savedUser.getName());
        return response;
    }

    public void farmerRegister(FarmerRegisDetails farmer) {
        userRepository.saveFarmerDetail(farmer);
    }

    public Map<String, Object> login(String phoneNumber, String password) {
        UserDtls user = userRepository.findByPhoneNumber(phoneNumber);
        if (user == null || !user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid phone number or password.");
        }
        String token = jwtUtil.generateToken(phoneNumber);
        Map<String, Object> response = new HashMap<>();
        response.put("userId", user.getId());
        response.put("name", user.getName());
        response.put("role", user.getRole());
        response.put("token", token);
        return response;
    }

    public List<String> getFarmerNamesByCropCode(String cropCode) {
        List<String> farmers = userRepository.findFarmerNamesByCropCode(cropCode);
        if (farmers.isEmpty()) {
            throw new ResourceNotFoundException("No farmers found for crop code: " + cropCode);
        }
        return farmers;
    }

    public List<BuyerDto> getAllBuyers() {
        List<UserDtls> buyers = userRepository.getAllBuyers();
        return buyers.stream().map(user -> {
            BuyerDto dto = new BuyerDto();
            dto.setId(user.getId());
            dto.setName(user.getName());
            dto.setPhoneNumber(user.getPhoneNumber());
            return dto;
        }).collect(Collectors.toList());
    }
}