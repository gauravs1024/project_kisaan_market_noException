package com.app.service;

import com.app.model.UserDtls;
import com.app.repository.UserRepository;
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

    public UserDtls login(String phoneNumber, String password) {
        UserDtls user = userRepository.findByPhoneNumber(phoneNumber);
        if (user != null && user.getPassword().equals(password)) {
            return user; 
        }
        return null; 
    }
}
