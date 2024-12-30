package com.example.garbagecollection.service;

import com.example.garbagecollection.repository.DashboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DashboardService {
    @Autowired
    private DashboardRepository dashboardRepository;

    public Map<String, Long> getDashboardMetrics() {
        Map<String, Long> metrics = new HashMap<>();
        metrics.put("totalAdmins",dashboardRepository.countADmins());
        metrics.put("totalDrivers", dashboardRepository.countDrivers());

        metrics.put("activeDrivers", dashboardRepository.countActiveDrivers());
        metrics.put("inactiveDrivers", dashboardRepository.countInactiveDrivers());
        metrics.put("totalVehicles", dashboardRepository.countVehicles());
        metrics.put("totalBins", dashboardRepository.countBins());
        metrics.put("binsAboveThreshold", dashboardRepository.countBinsAboveThreshold());
        metrics.put("binsInProgress",dashboardRepository.countBinsInProgressThreshold());
        metrics.put("binsBelowThreshold", dashboardRepository.countBinsBelowThreshold());
        metrics.put("vehiclesWithoutUser", dashboardRepository.countVehiclesWithoutUser());
        return metrics;
    }
}

