package com.example.garbagecollection.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeploymentController {

    @GetMapping("/")
    @Operation(summary = "sample deployment code testing", description = "sample data")
    public String deploymentTesting() {
        return "Application deployed sucessfully";
    }
}