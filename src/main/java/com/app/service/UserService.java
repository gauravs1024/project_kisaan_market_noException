package com.app.service;

import com.app.dto.BuyerDto;
import com.app.model.FarmerRegisDetails;
import com.app.model.UserDtls;
import com.app.repository.UserRepository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Map<String, Object> registerUser(UserDtls user) {
        userRepository.save(user); // Save the user to the database
        UserDtls savedUser = userRepository.findByPhoneNumber(user.getPhoneNumber()); // Retrieve saved user details
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("Id", savedUser.getId());
        response.put("name", savedUser.getName());
        return response;
    }
    
    

    public void farmerRegister(FarmerRegisDetails farmer){
        userRepository.saveFarmerDetail(farmer);
    }

    public Map<String, Object> login(String phoneNumber, String password) {
    UserDtls user = userRepository.findByPhoneNumber(phoneNumber);
    if (user != null && user.getPassword().equals(password)) {
        
        Map<String, Object> userData = new LinkedHashMap<>();
        userData.put("id", user.getId());
        userData.put("name", user.getName());
        userData.put("role", user.getRole());
        return userData;
    }
    return null; 
}

    public List<String> getFarmerNamesByCropCode(String cropCode) {
        return userRepository.findFarmerNamesByCropCode(cropCode);
    }
     public List<BuyerDto> getAllBuyers() {
        List<UserDtls> buyers = userRepository.getAllBuyers();

        // Map UserDtls to BuyerDto
        return buyers.stream().map(user -> {
            BuyerDto dto = new BuyerDto();
            dto.setId(user.getId());
            dto.setName(user.getName());
            dto.setPhoneNumber(user.getPhoneNumber());
            return dto;
        }).collect(Collectors.toList());
    }
}
