package com.example.farmapp.Service;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.example.farmapp.Domain.WorkDomain;
import com.example.farmapp.Entity.Work;

public interface WorkService {

    public String createWork(WorkDomain workData);

    public Set<Map<String, String>> getWork(Long uid);

    public Optional<Work> findWorkById(Long workId);

    public void deleteWork(Long workId);

}