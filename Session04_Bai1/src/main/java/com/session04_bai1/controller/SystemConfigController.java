package com.session04_bai1.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * REST Controller providing endpoint to inspect current active Spring Boot profile
 * and LLM configuration for AI Logistics Incident Reporter.
 */
@RestController
@RequestMapping("/api/v1/incident")
public class SystemConfigController {

    private final Environment environment;

    @Value("${spring.application.name:logistics-incident-reporter}")
    private String applicationName;

    @Value("${spring.ai.provider:Unknown}")
    private String aiProvider;

    @Value("${spring.ai.model.name:Unknown}")
    private String modelName;

    @Value("${spring.ai.base-url:Unknown}")
    private String baseUrl;

    public SystemConfigController(Environment environment) {
        this.environment = environment;
    }

    /**
     * Endpoint to fetch current system configuration and verify active LLM profile.
     * @return System configuration metadata
     */
    @GetMapping("/config")
    public ResponseEntity<Map<String, Object>> getSystemConfig() {
        String[] activeProfiles = environment.getActiveProfiles();
        String activeProfileStr = activeProfiles.length > 0 ? String.join(", ", activeProfiles) : "default";

        Map<String, Object> response = new HashMap<>();
        response.put("applicationName", applicationName);
        response.put("activeProfile", activeProfileStr);
        response.put("aiProvider", aiProvider);
        response.put("activeModelName", modelName);
        response.put("baseUrl", baseUrl);
        response.put("timestamp", LocalDateTime.now().toString());
        response.put("status", "SUCCESS");
        response.put("message", "Profile configuration verified successfully.");

        return ResponseEntity.ok(response);
    }
}
