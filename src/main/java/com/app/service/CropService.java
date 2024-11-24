package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.Crop;
import com.app.repository.CropRepository;

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
}
