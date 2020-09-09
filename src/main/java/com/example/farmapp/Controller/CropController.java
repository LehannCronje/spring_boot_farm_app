package com.example.farmapp.Controller;

import java.util.List;

import com.example.farmapp.Service.CropService;
import com.example.farmapp.dto.CropReqDTO;
import com.example.farmapp.dto.CropResDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/crop")
public class CropController {

    @Autowired
    private CropService cropService;

    @PostMapping("/")
    public void create(@RequestBody() CropReqDTO cropReqDTO) {
        cropService.createCrop(cropReqDTO);
    }

    @GetMapping("/{farmId}")
    public List<CropResDTO> getCrops(@PathVariable("farmId") Long farmId) {
        return cropService.getCrops(farmId);
    }

    @DeleteMapping("/{cropId}")
    public void deleteCrop(@PathVariable("cropId") Long cropId) {
        cropService.deleteCrop(cropId);
    }
}