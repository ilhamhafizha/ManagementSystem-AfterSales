package org.msas.managementsystemaftersales.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @GetMapping("/health")
    public String health() {
        return "BAST Monitoring Service is running";
    }
}