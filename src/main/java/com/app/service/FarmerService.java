package com.app.service;

import com.app.model.Crop;
import com.app.model.FarmerRegisDetails;
import com.app.model.UserDtls;
import com.app.repository.FarmerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FarmerService {

    @Autowired
    private FarmerRepository farmerRepository;

    public Map<String, Object> getFarmerDetails(int farmerId) {
        // Fetch farmer name
        UserDtls farmer = farmerRepository.getFarmerById(farmerId);

        // Fetch farmer registration details
        FarmerRegisDetails registrationDetails = farmerRepository.getFarmerRegistrationDetails(farmerId);

        // Fetch farmer crops
        List<Crop> crops = farmerRepository.getCropsByFarmerId(farmerId);

        // Combine all data into a map
        Map<String, Object> response = new HashMap<>();
        response.put("farmerName", farmer.getName());
        response.put("registrationDetails", registrationDetails);
        response.put("crops", crops);

        return response;
    }
}
