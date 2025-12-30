package org.msas.managementsystemaftersales.controller;

import lombok.RequiredArgsConstructor;
import org.msas.managementsystemaftersales.entity.ServiceSchedule;
import org.msas.managementsystemaftersales.repository.ServiceScheduleRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service-schedules")
@RequiredArgsConstructor
public class ServiceScheduleController {

    private final ServiceScheduleRepository repository;

    @GetMapping
    public List<ServiceSchedule> getAll() {
        return repository.findAll();
    }
}
