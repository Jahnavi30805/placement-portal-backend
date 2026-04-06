package com.placement.portal.controller;

import com.placement.portal.dto.ResumeAnalysisRequest;
import com.placement.portal.dto.ResumeAnalysisResponse;
import com.placement.portal.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resume")
@CrossOrigin(origins = "*")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    @PostMapping("/analyze")
    public ResponseEntity<ResumeAnalysisResponse> analyzeResume(@RequestBody ResumeAnalysisRequest request) {
        if (request == null || request.getResumeText() == null || request.getJobDescription() == null) {
            return ResponseEntity.badRequest().build();
        }

        ResumeAnalysisResponse response = resumeService.analyzeResume(
                request.getResumeText(),
                request.getJobDescription()
        );

        return ResponseEntity.ok(response);
    }
}
