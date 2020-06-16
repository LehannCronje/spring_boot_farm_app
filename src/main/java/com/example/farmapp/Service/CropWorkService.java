package com.example.farmapp.Service;

import java.util.Optional;

import com.example.farmapp.Entity.CropWork;

public interface CropWorkService {

    public Optional<CropWork> findCropWorkBySiteCropAndWork(Long siteCropId, Long workId);

    public void insertCropWork(CropWork cropWork);

}