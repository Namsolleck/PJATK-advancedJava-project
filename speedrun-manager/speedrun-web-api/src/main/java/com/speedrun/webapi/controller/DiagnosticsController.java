package com.speedrun.webapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/diagnostics")
public class DiagnosticsController {

    @GetMapping
    public String status() {
        return "ALIVE";
    }

    @GetMapping("/check")
    public Map<String, Object> checkDiskSpace() {
        File file = new File("/");
        long totalSpace = file.getTotalSpace();
        long freeSpace = file.getFreeSpace();
        double freePercentage = ((double) freeSpace / totalSpace) * 100;

        Map<String, Object> response = new HashMap<>();
        response.put("status", freePercentage > 5 ? "OK" : "LOW_SPACE");
        response.put("freePercentage", String.format("%.2f%%", freePercentage));
        response.put("minRequired", "5%");

        return response;
    }
}