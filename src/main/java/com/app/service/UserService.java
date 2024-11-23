package com.app.service;

import com.app.model.FarmerRegisDetails;
import com.app.model.UserDtls;
import com.app.repository.UserRepository;

import java.util.List;

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

    public UserDtls login(String phoneNumber, String password) {
        UserDtls user = userRepository.findByPhoneNumber(phoneNumber);
        if (user != null && user.getPassword().equals(password)) {
            return user; 
        }
        return null; 
    }
    public List<String> getFarmerNamesByCropCode(String cropCode) {
        return userRepository.findFarmerNamesByCropCode(cropCode);
    }
}
