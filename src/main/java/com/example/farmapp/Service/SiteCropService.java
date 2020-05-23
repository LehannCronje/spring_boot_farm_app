package com.example.farmapp.Service;

import java.util.Set;

import com.example.farmapp.Entity.Crop;
import com.example.farmapp.Entity.SiteCrop;

public interface SiteCropService {

	Set<Crop> getSiteCrops(Long farmId, Long siteId);

	public String insertSiteCrop(SiteCrop siteCrop);

}
