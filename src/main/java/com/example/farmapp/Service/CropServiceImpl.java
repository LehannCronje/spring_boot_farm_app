package com.example.farmapp.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.farmapp.Entity.Crop;
import com.example.farmapp.Entity.Farm;
import com.example.farmapp.Entity.FarmSite;
import com.example.farmapp.Entity.Site;
import com.example.farmapp.Entity.SiteCrop;
import com.example.farmapp.Repository.CropRepository;
import com.example.farmapp.dto.CropReqDTO;
import com.example.farmapp.dto.CropResDTO;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CropServiceImpl implements CropService {

	@Autowired
	private CropRepository cropRepo;

	@Autowired
	private FarmService farmService;

	@Autowired
	private SiteService siteService;

	@Autowired
	private FarmSiteService farmSiteService;

	@Autowired
	private SiteCropService siteCropService;

	public String createCrop(CropReqDTO cropReqDTO) {

		Farm farm = farmService.findFarmById(cropReqDTO.getFarmId()).get();
		Site site = siteService.findSiteByName("UNNASIGNED-TEMP").orElse(null);
		Crop crop = new Crop();
		SiteCrop siteCrop = new SiteCrop();

		if (site == null) {
			site = new Site();
			site.setName("UNNASIGNED-TEMP");
			siteService.insertSite(site);
		}

		FarmSite farmSite = farmSiteService.findFarmSiteByFarmIdAndSiteId(farm.getId(), site.getId()).orElse(null);
		System.out.println(farmSite);

		if (farmSite == null) {
			farmSite = new FarmSite();
			farmSite.setFarm(farm);
			farmSite.setSite(site);
			farmSiteService.insertFarmSite(farmSite);
		}

		crop.setName(cropReqDTO.getCropName());
		this.insertCrop(crop);

		siteCrop.setFarmSite(farmSite);
		siteCrop.setCrop(crop);
		siteCropService.insertSiteCrop(siteCrop);

		return "success";
	}

	public List<CropResDTO> getCrops(Long farmId) {
		// fix nullpointer
		try {
			return this.findCropsByFarmId(farmId).stream().map(crop -> {
				CropResDTO cropResDTO = new CropResDTO();
				BeanUtils.copyProperties(crop, cropResDTO);
				return cropResDTO;
			}).collect(Collectors.toList());
		} catch (Exception e) {
			return new ArrayList<CropResDTO>();
		}
	}

	// crud operations
	public String insertCrop(Crop crop) {
		try {
			cropRepo.save(crop);
			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "Failure";
		}
	}

	public List<Crop> findCropsByFarmId(Long farmId) {
		// fix nullpointer
		try {
			Farm farm = farmService.findFarmById(farmId).get();
			Site site = siteService.findSiteByName("UNNASIGNED-TEMP").orElse(null);
			FarmSite farmSite = farmSiteService.findFarmSiteByFarmIdAndSiteId(farm.getId(), site.getId()).orElse(null);
			if (farmSite == null) {
				return new ArrayList<Crop>();
			} else {
				return farmSite.getSiteCrop().stream().map(SiteCrop::getCrop)
						.filter(crop -> !("UNNASIGNED-TEMP").equals(crop.getName())).collect(Collectors.toList());

			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Crop>();
		}

	}

	@Override
	public Optional<Crop> findCropById(Long cropId) {

		return cropRepo.findById(cropId);
	}

}