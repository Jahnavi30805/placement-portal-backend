package com.placement.portal.service;

import com.placement.portal.dto.ResumeAnalysisResponse;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ResumeService {

    // Define some core skills for bonus points
    private static final Set<String> CORE_SKILLS = new HashSet<>(Arrays.asList(
            "java", "python", "javascript", "react", "spring", "sql", "mysql",
            "aws", "docker", "kubernetes", "agile", "html", "css", "c++", "c#", "node"
    ));

    public ResumeAnalysisResponse analyzeResume(String resumeText, String jobDescription) {
        if (resumeText == null || resumeText.trim().isEmpty()) {
            return new ResumeAnalysisResponse(0.0, new ArrayList<>(), 0.0);
        }
        if (jobDescription == null || jobDescription.trim().isEmpty()) {
            return new ResumeAnalysisResponse(100.0, new ArrayList<>(), 100.0); // No job description, perfect match? Or maybe handle differently. Let's return 0 for safe.
        }

        // Clean texts: punctuation to space, then split by whitespace
        String cleanResume = resumeText.toLowerCase().replaceAll("[^a-z0-9]", " ");
        String cleanJob = jobDescription.toLowerCase().replaceAll("[^a-z0-9]", " ");

        Set<String> resumeWords = new HashSet<>(Arrays.asList(cleanResume.split("\\s+")));
        Set<String> jobWords = new HashSet<>(Arrays.asList(cleanJob.split("\\s+")));

        resumeWords.remove("");
        jobWords.remove("");

        // Also exclude common stop words if needed, but going with simplest word matching here
        Set<String> stopWords = new HashSet<>(Arrays.asList("and", "or", "the", "a", "an", "in", "on", "for", "with", "to", "of"));
        jobWords.removeAll(stopWords);
        resumeWords.removeAll(stopWords);

        int totalJobWords = jobWords.size();
        if (totalJobWords == 0) {
            return new ResumeAnalysisResponse(0.0, new ArrayList<>(), 0.0);
        }

        int matchedCount = 0;
        List<String> missingSkills = new ArrayList<>();
        boolean hasCoreSkills = false;

        for (String jWord : jobWords) {
            if (resumeWords.contains(jWord)) {
                matchedCount++;
                if (CORE_SKILLS.contains(jWord)) {
                    hasCoreSkills = true;
                }
            } else {
                missingSkills.add(jWord);
            }
        }

        // Calculate Match Percentage
        double matchPercentage = ((double) matchedCount / totalJobWords) * 100.0;
        
        // Formatting percentage to 2 decimal places
        matchPercentage = Math.round(matchPercentage * 100.0) / 100.0;

        // Calculate ATS Score: match percentage + bonus
        double atsScore = matchPercentage;
        if (hasCoreSkills) {
            atsScore += 15.0; // Bonus points (10-20) if core skills present
        }

        // Cap ATS score at 100
        if (atsScore > 100.0) {
            atsScore = 100.0;
        }

        return new ResumeAnalysisResponse(matchPercentage, missingSkills, atsScore);
    }
}
