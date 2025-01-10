package com.example.garbagecollection.controller;

import com.example.garbagecollection.entity.Bin;
import com.example.garbagecollection.service.BinService;
import com.example.garbagecollection.util.GeoUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/routes")
@SecurityRequirement(name = "basicAuth")
public class RoutingController {

    @Autowired
    private BinService binService;

    @GetMapping("/shortest")
    @Operation(summary = "shortest route", description = "get shortest route to bin")
    public ResponseEntity<List<Bin>> getShortestRoute(@RequestParam double driverLat, @RequestParam double driverLong) {
        List<Bin> bins = binService.getAllBins();
        List<Bin> shortestRoute = GeoUtils.calculateShortestRoute(driverLat, driverLong, bins);
        return ResponseEntity.ok(shortestRoute);
    }
}
