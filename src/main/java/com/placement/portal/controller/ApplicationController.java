package com.placement.portal.controller;

import com.placement.portal.entity.Application;
import com.placement.portal.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin(origins = "*")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @PostMapping("/apply")
    public ResponseEntity<Application> applyJob(@RequestBody Map<String, Long> payload) {
        Long userId = payload.get("userId");
        Long jobId = payload.get("jobId");
        
        if (userId == null || jobId == null) {
            return ResponseEntity.badRequest().build();
        }
        
        Application application = applicationService.applyJob(userId, jobId);
        return ResponseEntity.ok(application);
    }

    @GetMapping
    public ResponseEntity<List<Application>> getAllApplications() {
        List<Application> applications = applicationService.getAllApplications();
        return ResponseEntity.ok(applications);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Application> updateStatus(@PathVariable Long id, @RequestBody Map<String, String> payload) {
        String status = payload.get("status");
        
        if (status == null) {
            return ResponseEntity.badRequest().build();
        }
        
        Application application = applicationService.updateStatus(id, status);
        if (application != null) {
            return ResponseEntity.ok(application);
        }
        // Either not found or invalid status
        return ResponseEntity.badRequest().build(); // Standardizing response if status is rejected/invalid
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Application>> getApplicationsByUser(@PathVariable Long userId) {
        List<Application> applications = applicationService.getApplicationsByUser(userId);
        return ResponseEntity.ok(applications);
    }

    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<Application>> getApplicationsByJob(@PathVariable Long jobId) {
        List<Application> applications = applicationService.getApplicationsByJob(jobId);
        return ResponseEntity.ok(applications);
    }
}
