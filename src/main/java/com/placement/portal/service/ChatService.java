package com.placement.portal.service;

import org.springframework.stereotype.Service;

@Service
public class ChatService {

    public String getChatResponse(String message) {
        if (message == null || message.trim().isEmpty()) {
            return "Please ask questions related to placement portal";
        }

        String lowerCaseMessage = message.toLowerCase();

        if (lowerCaseMessage.contains("apply")) {
            return "Go to jobs page and click apply";
        } else if (lowerCaseMessage.contains("resume")) {
            return "Upload resume in profile section";
        } else if (lowerCaseMessage.contains("job")) {
            return "Browse jobs in dashboard";
        } else {
            return "Please ask questions related to placement portal";
        }
    }
}
