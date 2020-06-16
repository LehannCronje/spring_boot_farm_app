package com.example.farmapp.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.farmapp.Entity.Crop;
import com.example.farmapp.Entity.SiteCrop;
import com.example.farmapp.Repository.FarmSiteRepository;
import com.example.farmapp.Repository.SiteCropRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SiteCropServiceImpl implements SiteCropService {

	@Autowired
	FarmSiteRepository farmSiteRepo;

	@Autowired
	SiteCropRepository siteCropRepo;

	@Autowired
	FarmSiteService farmSiteService;

	@Override
	public Set<Crop> getSiteCrops(Long farmId, Long siteId) {
		System.out.println(farmId + " " + siteId);
		if (farmSiteRepo.findByFarmIdAndSiteId(farmId, siteId).get().getSiteCrop() == null) {
			return new HashSet<Crop>();
		} else {
			// error testing needed
			return farmSiteRepo.findByFarmIdAndSiteId(farmId, siteId).get().getSiteCrop().stream()
					.map(SiteCrop::getCrop).collect(Collectors.toSet());
		}

	}

	@Override
	public String insertSiteCrop(SiteCrop siteCrop) {

		try {
			siteCropRepo.save(siteCrop);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "failure";
		}

	}

	@Override
	public Optional<SiteCrop> findSiteCropById(Long siteCropId) {
		return siteCropRepo.findById(siteCropId);
	}

	@Override
	public Optional<SiteCrop> findByFarmSiteIdAndCropId(Long farmSiteId, Long cropId) {
		return siteCropRepo.findByFarmSiteIdAndCropId(farmSiteId, cropId);
	}

}
