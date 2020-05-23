package com.example.farmapp.Service;

import java.util.Map;
import java.util.Set;

import com.example.farmapp.Domain.WorkDomain;

public interface WorkService {

    public String createWork(WorkDomain workData);

    public Set<Map<String, String>> getWork(Long uid);
}