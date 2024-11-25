package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.Crop;
import com.app.model.CropDetailsResponse;
import com.app.model.UserDtls;
import com.app.repository.CropRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CropService {

    @Autowired
    private CropRepository cropRepository;

    
    public int addCrop(Crop crop) {
        return cropRepository.addCrop(crop);
    }

    
    public List<Crop> getCrops(Long farmerId) {
        return cropRepository.getCrops(farmerId);
    }

    
    public Crop getCropById(Long cropId) {
        return cropRepository.getCropById(cropId);
    }
    
    public List<Crop> getAllCrops(Long farmerId) {
        return cropRepository.getCropsByFarmerId(farmerId);
    }

    public Crop getCropByCode(String cropCode) {
        return cropRepository.findCropByCode(cropCode);
    }
   
    public int updateCrop(Long cropId, Crop crop) {
        return cropRepository.updateCrop(cropId, crop);
    }

   
    public int deleteCrop(Long cropId) {
        return cropRepository.deleteCrop(cropId);
    }

     public List<CropDetailsResponse> getCropDetails(String cropCode) {
        // Fetch crops with the given cropCode
        List<Crop> crops = cropRepository.findCropsByCode(cropCode);

        if (crops.isEmpty()) {
            throw new RuntimeException("No crops found with code: " + cropCode);
        }

        // Create responses for each crop
        List<CropDetailsResponse> responses = new ArrayList<>();

        for (Crop crop : crops) {
            // Fetch farmer details
            UserDtls farmer = cropRepository.findFarmerById(crop.getFarmerId());

            if (farmer == null) {
                throw new RuntimeException("Farmer not found with ID: " + crop.getFarmerId());
            }

            // Create response
            responses.add(new CropDetailsResponse(
                    crop.getFarmerId(),
                    farmer.getName(),
                    crop.getPrice(),
                    crop.getQuantity()
            ));
        }

        return responses;
    }
}
