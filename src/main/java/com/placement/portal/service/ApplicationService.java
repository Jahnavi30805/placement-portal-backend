package com.placement.portal.service;

import com.placement.portal.entity.Application;
import com.placement.portal.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    public Application applyJob(Long userId, Long jobId) {
        Application application = new Application();
        application.setUserId(userId);
        application.setJobId(jobId);
        application.setStatus("APPLIED"); // default status
        return applicationRepository.save(application);
    }

    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    public Application updateStatus(Long id, String status) {
        Optional<Application> optionalApplication = applicationRepository.findById(id);
        if (optionalApplication.isPresent() && status != null) {
            Application application = optionalApplication.get();
            List<String> validStatuses = Arrays.asList("APPLIED", "SHORTLISTED", "SELECTED", "REJECTED");
            
            // Format to uppercase to match standard
            String formattedStatus = status.toUpperCase();
            
            if (validStatuses.contains(formattedStatus)) {
                application.setStatus(formattedStatus);
                return applicationRepository.save(application);
            }
        }
        return null;
    }

    public List<Application> getApplicationsByUser(Long userId) {
        return applicationRepository.findByUserId(userId);
    }

    public List<Application> getApplicationsByJob(Long jobId) {
        return applicationRepository.findByJobId(jobId);
    }
}
