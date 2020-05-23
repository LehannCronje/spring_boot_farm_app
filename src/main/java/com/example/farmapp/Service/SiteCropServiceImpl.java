package com.example.farmapp.Service;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.farmapp.Entity.Crop;
import com.example.farmapp.Entity.SiteCrop;
import com.example.farmapp.Repository.FarmSiteRepository;
import com.example.farmapp.Repository.SiteCropRepository;

@Service
public class SiteCropServiceImpl implements SiteCropService {

	@Autowired
	FarmSiteRepository farmSiteRepo;

	@Autowired
	SiteCropRepository siteCropService;

	@Override
	public Set<Crop> getSiteCrops(Long farmId, Long siteId) {
		System.out.println(farmId + " " + siteId);
		System.out.println(farmSiteRepo.findByFarmIdAndSiteId(farmId, siteId).toString());

		return farmSiteRepo.findByFarmIdAndSiteId(farmId, siteId).stream()
				.flatMap(farmSite -> farmSite.getSiteCrop().stream()).map(SiteCrop::getCrop)
				.collect(Collectors.toSet());
	}

	@Override
	public String insertSiteCrop(SiteCrop siteCrop) {

		try {
			siteCropService.save(siteCrop);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "failure";
		}

	}

}
