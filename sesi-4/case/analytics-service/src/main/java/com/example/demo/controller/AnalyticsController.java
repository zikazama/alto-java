package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnalyticsController {
    @GetMapping("/analytics")
    public String getAnalytics() {
        return "Analytics data";
    }
}