package com.example.farmapp.Service;

import java.util.List;

import com.example.farmapp.Entity.Crop;
import com.example.farmapp.dto.CropReqDTO;
import com.example.farmapp.dto.CropResDTO;

public interface CropService {

    public String createCrop(CropReqDTO cropReqDTO);

    public List<CropResDTO> getCrops(Long uid);

    public String insertCrop(Crop crop);

    public List<Crop> findCropsByFarmId(Long farmId);

}