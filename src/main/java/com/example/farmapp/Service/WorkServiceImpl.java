package com.example.farmapp.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.example.farmapp.Domain.WorkDomain;
import com.example.farmapp.Entity.Crop;
import com.example.farmapp.Entity.CropWork;
import com.example.farmapp.Entity.Farm;
import com.example.farmapp.Entity.FarmSite;
import com.example.farmapp.Entity.Site;
import com.example.farmapp.Entity.SiteCrop;
import com.example.farmapp.Entity.Work;
import com.example.farmapp.Repository.CropRepository;
import com.example.farmapp.Repository.CropWorkRepository;
import com.example.farmapp.Repository.FarmRepository;
import com.example.farmapp.Repository.FarmSiteRepository;
import com.example.farmapp.Repository.SiteCropRepository;
import com.example.farmapp.Repository.SiteRepository;
import com.example.farmapp.Repository.WorkRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkServiceImpl implements WorkService {

    @Autowired
    private SiteRepository siteRepo;

    @Autowired
    private FarmSiteRepository farmSiteRepo;

    @Autowired
    private FarmRepository farmRepo;

    @Autowired
    private CropRepository cropRepo;

    @Autowired
    private SiteCropRepository siteCropRepo;

    @Autowired
    private CropWorkRepository cropWorkRepo;

    @Autowired
    private WorkRepository workRepo;

    @Autowired
    private FarmSiteService farmSiteService;

    public String createWork(WorkDomain workData) {

        Site site = siteRepo.findByName("UNNASIGNED-TEMP").orElse(null);
        Farm farm = farmRepo.findById(workData.getFarmId()).get();
        Work work = new Work();
        work.setName(workData.getWorkName());

        if (site == null) {
            site = new Site();
            site.setName("UNNASIGNED-TEMP");
            siteRepo.save(site);
        }

        FarmSite farmSite = farmSiteService.findFarmSiteByFarmIdAndSiteId(farm.getId(), site.getId()).orElse(null);

        if (farmSite == null) {
            farmSite = new FarmSite();
            farmSite.setFarm(farm);
            farmSite.setSite(site);
            farmSiteService.insertFarmSite(farmSite);
        }

        Crop crop = cropRepo.findByName("UNNASIGNED-TEMP").orElse(null);
        if (crop == null) {
            crop = new Crop();
            crop.setName("UNNASIGNED-TEMP");
            cropRepo.save(crop);
        }

        SiteCrop siteCrop = siteCropRepo.findByFarmSiteIdAndCropId(farmSite.getId(), crop.getId()).orElse(null);

        if (siteCrop == null) {
            siteCrop = new SiteCrop();
            siteCrop.setFarmSite(farmSite);
            siteCrop.setCrop(crop);
            siteCropRepo.save(siteCrop);
        }

        workRepo.save(work);

        CropWork cropWork = new CropWork();
        cropWork.setSiteCrop(siteCrop);
        cropWork.setWork(work);
        cropWorkRepo.save(cropWork);

        return "Success";
    }

    @Override
    public Set<Map<String, String>> getWork(Long farmId) {
        Set<Map<String, String>> workDataSet = new HashSet<Map<String, String>>();
        Map<String, String> singularWorkMap = new HashMap<String, String>();
        Farm farm = farmRepo.findById(farmId).get();

        for (FarmSite farmSite : farm.getFarmSite()) {
            for (SiteCrop siteCrop : farmSite.getSiteCrop()) {
                for (CropWork cropWork : siteCrop.getCropWork()) {

                    System.out.println(cropWork.getWork().getName());
                    singularWorkMap = new HashMap<String, String>();
                    singularWorkMap.put("id", "" + cropWork.getWork().getId());
                    singularWorkMap.put("name", cropWork.getWork().getName());
                    workDataSet.add(singularWorkMap);
                }
            }
        }

        return workDataSet;
    }

    public Optional<Work> findWorkById(Long workId) {

        return workRepo.findById(workId);
    }

}