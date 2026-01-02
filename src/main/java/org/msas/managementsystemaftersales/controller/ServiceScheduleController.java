package org.msas.managementsystemaftersales.controller;

import lombok.RequiredArgsConstructor;
import org.msas.managementsystemaftersales.dto.ServiceScheduleResponse;
import org.msas.managementsystemaftersales.service.ServiceScheduleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service-schedules")
@RequiredArgsConstructor
public class ServiceScheduleController {

    private final ServiceScheduleService service;

    @GetMapping
    public List<ServiceScheduleResponse> getAll() {
        return service.getAll();
    }
}
