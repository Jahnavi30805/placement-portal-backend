package com.placement.portal.dto;

import java.util.List;

public class ResumeAnalysisResponse {

    private double matchPercentage;
    private List<String> missingSkills;
    private double atsScore;

    public ResumeAnalysisResponse() {
    }

    public ResumeAnalysisResponse(double matchPercentage, List<String> missingSkills, double atsScore) {
        this.matchPercentage = matchPercentage;
        this.missingSkills = missingSkills;
        this.atsScore = atsScore;
    }

    public double getMatchPercentage() {
        return matchPercentage;
    }

    public void setMatchPercentage(double matchPercentage) {
        this.matchPercentage = matchPercentage;
    }

    public List<String> getMissingSkills() {
        return missingSkills;
    }

    public void setMissingSkills(List<String> missingSkills) {
        this.missingSkills = missingSkills;
    }

    public double getAtsScore() {
        return atsScore;
    }

    public void setAtsScore(double atsScore) {
        this.atsScore = atsScore;
    }
}
