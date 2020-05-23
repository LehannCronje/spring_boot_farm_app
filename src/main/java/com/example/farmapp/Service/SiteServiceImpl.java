package com.example.farmapp.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.example.farmapp.Domain.SiteDomain;
import com.example.farmapp.Entity.Farm;
import com.example.farmapp.Entity.FarmSite;
import com.example.farmapp.Entity.Site;
import com.example.farmapp.Entity.SiteCrop;
import com.example.farmapp.Repository.CropRepository;
import com.example.farmapp.Repository.FarmRepository;
import com.example.farmapp.Repository.FarmSiteRepository;
import com.example.farmapp.Repository.SiteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SiteServiceImpl implements SiteService {

	@Autowired
	FarmRepository farmRepo;

	@Autowired
	SiteRepository siteRepo;

	@Autowired
	CropRepository cropRepo;

	@Autowired
	FarmSiteRepository farmSiteRepo;

	@Override
	public String createSite(SiteDomain siteData) {

		Farm f = farmRepo.findById(siteData.getFarmId()).get();
		FarmSite farmSite = new FarmSite();
		Site s = new Site();
		Set<FarmSite> farmSites = new HashSet<FarmSite>();

		s.setName(siteData.getSiteName());
		siteRepo.save(s);

		farmSite.setFarm(f);
		farmSite.setSite(s);
		farmSiteRepo.save(farmSite);

		return "Success";
	}

	@Override
	public Set<Map<String, String>> getAllSites(Long uid) {

		Set<Map<String, String>> s = new HashSet<Map<String, String>>();
		Map<String, String> m = new HashMap<String, String>();
		Farm farm = farmRepo.findById(uid).get();

		for (FarmSite farmSite : farm.getFarmSite()) {
			if (!farmSite.getSite().getName().equals("UNNASIGNED-TEMP")) {
				m = new HashMap<String, String>();
				m.put("farmSiteId", "" + farmSite.getId());
				m.put("id", "" + farmSite.getSite().getId());
				m.put("name", farmSite.getSite().getName());
				s.add(m);
			}
		}

		return s;
	}

	@Override
	public void deleteSite(Long id) {

		siteRepo.deleteById(id);

	}

	public Site findSiteById(Long siteId) {
		return siteRepo.findById(siteId).get();
	}

	public void addCrops(SiteDomain siteData) {

		FarmSite farmSite = farmSiteRepo.findById(siteData.getSiteId()).get();
		System.out.println(siteData.toString());

		Set<SiteCrop> siteCrops = farmSite.getSiteCrop();

		for (Long cropId : siteData.getCropIds()) {
			SiteCrop siteCrop = new SiteCrop();
			siteCrop.setCrop(cropRepo.findById(cropId).get());
			siteCrop.setFarmSite(farmSite);
			siteCrops.add(siteCrop);
		}

		farmSite.setSiteCrop(siteCrops);
		farmSiteRepo.save(farmSite);

	}

	public Optional<Site> findSiteByName(String name) {
		return siteRepo.findByName(name);
	}

	public String insertSite(Site site) {
		try {
			siteRepo.save(site);
			return "Success";

		} catch (Exception e) {
			e.printStackTrace();
			return "failure";
		}

	}
}
