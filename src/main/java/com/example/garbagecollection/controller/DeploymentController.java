package com.example.garbagecollection.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeploymentController {

    @GetMapping("/")
    public String deploymentTesting() {
        return "Application deployed sucessfully";
    }
}