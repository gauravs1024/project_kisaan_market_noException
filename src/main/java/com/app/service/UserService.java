package com.app.service;

import com.app.model.FarmerRegisDetails;
import com.app.model.UserDtls;
import com.app.repository.UserRepository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(UserDtls user) {
        userRepository.save(user);
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
}
