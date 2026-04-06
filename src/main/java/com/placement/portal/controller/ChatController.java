package com.placement.portal.controller;

import com.placement.portal.dto.ChatRequest;
import com.placement.portal.dto.ChatResponse;
import com.placement.portal.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "*")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping
    public ResponseEntity<ChatResponse> chat(@RequestBody ChatRequest request) {
        if (request == null || request.getMessage() == null) {
            return ResponseEntity.badRequest().build();
        }

        String responseText = chatService.getChatResponse(request.getMessage());
        return ResponseEntity.ok(new ChatResponse(responseText));
    }
}
