package com.example.farmapp.Service;

import java.util.Optional;
import java.util.Set;

import com.example.farmapp.Entity.Crop;
import com.example.farmapp.Entity.SiteCrop;

public interface SiteCropService {

	Set<Crop> getSiteCrops(Long farmId, Long siteId);

	public String insertSiteCrop(SiteCrop siteCrop);

	public Optional<SiteCrop> findSiteCropById(Long siteCropId);

	public Optional<SiteCrop> findByFarmSiteIdAndCropId(Long farmSiteId, Long cropId);

	public void deleteSiteCropByCropIdAndSiteId(Long cropId, Long siteId);
}
