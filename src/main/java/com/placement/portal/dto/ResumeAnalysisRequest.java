package com.placement.portal.dto;

public class ResumeAnalysisRequest {

    private String resumeText;
    private String jobDescription;

    public ResumeAnalysisRequest() {
    }

    public ResumeAnalysisRequest(String resumeText, String jobDescription) {
        this.resumeText = resumeText;
        this.jobDescription = jobDescription;
    }

    public String getResumeText() {
        return resumeText;
    }

    public void setResumeText(String resumeText) {
        this.resumeText = resumeText;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }
}
