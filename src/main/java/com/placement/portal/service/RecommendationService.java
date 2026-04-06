package com.placement.portal.service;

import com.placement.portal.entity.Job;
import com.placement.portal.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecommendationService {

    @Autowired
    private JobRepository jobRepository;

    public List<Job> recommendJobs(String skills) {
        List<Job> allJobs = jobRepository.findAll();
        
        if (skills == null || skills.trim().isEmpty() || allJobs.isEmpty()) {
            return allJobs;
        }

        // Extract lowercase, alphanumeric user skills
        String[] skillWords = skills.toLowerCase().replaceAll("[^a-z0-9]", " ").split("\\s+");
        Set<String> userSkills = new HashSet<>(Arrays.asList(skillWords));
        userSkills.remove("");

        Map<Job, Integer> matchScores = new HashMap<>();

        for (Job job : allJobs) {
            String title = job.getTitle() != null ? job.getTitle() : "";
            String desc = job.getDescription() != null ? job.getDescription() : "";
            
            // Combine title and description and extract words
            String combinedText = (title + " " + desc).toLowerCase().replaceAll("[^a-z0-9]", " ");
            Set<String> jobWords = new HashSet<>(Arrays.asList(combinedText.split("\\s+")));
            
            int score = 0;
            for (String skill : userSkills) {
                if (jobWords.contains(skill)) {
                    score++;
                }
            }
            matchScores.put(job, score);
        }

        // Sort all jobs based on their score in descending order
        allJobs.sort((job1, job2) -> Integer.compare(matchScores.get(job2), matchScores.get(job1)));

        return allJobs;
    }
}
