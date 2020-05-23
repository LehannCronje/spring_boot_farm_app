package com.example.farmapp.Domain;

import java.util.Set;

import lombok.Data;

@Data	
public class SiteDomain {

    private Long farmId;
    
    private Long siteId;

    private String siteName;
    
    private Set<Long> cropIds;

}