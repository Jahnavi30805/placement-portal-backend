package com.placement.portal.controller;

import com.placement.portal.dto.RecommendationRequest;
import com.placement.portal.entity.Job;
import com.placement.portal.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @PostMapping("/api/jobs/recommend")
    public ResponseEntity<List<Job>> recommendJobs(@RequestBody RecommendationRequest request) {
        if (request == null || request.getSkills() == null) {
            return ResponseEntity.badRequest().build();
        }

        List<Job> recommendedJobs = recommendationService.recommendJobs(request.getSkills());
        return ResponseEntity.ok(recommendedJobs);
    }
}
