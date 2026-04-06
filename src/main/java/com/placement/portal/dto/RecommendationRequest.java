package com.placement.portal.dto;

public class RecommendationRequest {

    private String skills;

    public RecommendationRequest() {
    }

    public RecommendationRequest(String skills) {
        this.skills = skills;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
}
