package com.example.farmapp.Service;

import java.util.Optional;

import com.example.farmapp.Entity.CropWork;
import com.example.farmapp.Repository.CropWorkRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CropWorkServiceImpl implements CropWorkService {

    @Autowired
    private CropWorkRepository cropWorkRepo;

    @Override
    public Optional<CropWork> findCropWorkBySiteCropAndWork(Long siteCropId, Long workId) {

        return cropWorkRepo.findBySiteCropIdAndWorkId(siteCropId, workId);

    }

    @Override
    public void insertCropWork(CropWork cropWork) {
        cropWorkRepo.save(cropWork);

    }

}