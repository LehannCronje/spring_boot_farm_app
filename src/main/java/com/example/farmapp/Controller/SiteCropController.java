package com.example.farmapp.Controller;

import java.util.Set;

import com.example.farmapp.Entity.Crop;
import com.example.farmapp.Service.SiteCropService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/site-crop")
public class SiteCropController {

	@Autowired
	SiteCropService siteCropService;

	@GetMapping("/farm/{farmId}/site/{siteId}")
	public Set<Crop> getSiteCrops(@PathVariable("farmId") Long farmId, @PathVariable("siteId") Long siteId) {
		return siteCropService.getSiteCrops(farmId, siteId);

	}

	@DeleteMapping("/crop/{cropId}/site/{siteId}")
	public void deleteSiteCrop(@PathVariable("cropId") Long cropId, @PathVariable("siteId") Long siteId) {

		siteCropService.deleteSiteCropByCropIdAndSiteId(cropId, siteId);

	}

	// @GetMapping("/")

}
