package org.msas.managementsystemaftersales.controller;

import lombok.RequiredArgsConstructor;
import org.msas.managementsystemaftersales.dto.ServiceScheduleResponse;
import org.msas.managementsystemaftersales.entity.ServiceSchedule;
import org.msas.managementsystemaftersales.repository.ServiceScheduleRepository;
import org.msas.managementsystemaftersales.service.ServiceScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/service-schedules")
@RequiredArgsConstructor
public class ServiceScheduleController {

    private final ServiceScheduleRepository repository;

    @GetMapping
    public ResponseEntity<List<ServiceScheduleResponse>> getAllServiceSchedules() {

        List<ServiceScheduleResponse> result =
                repository.findAll()
                        .stream()
                        .map(s -> new ServiceScheduleResponse(
                                s.getId(),
                                s.getJadwalService(),
                                s.getServiceType(),
                                s.getStatusService()
                        ))
                        .toList();

        return ResponseEntity.ok(result);
    }


    @PutMapping("/{id}/complete")
    public ResponseEntity<?> completeService(@PathVariable Long id) {

        ServiceSchedule schedule = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service schedule not found"));

        if (schedule.getStatusService()) {
            return ResponseEntity.badRequest().body("Service already completed");
        }

        schedule.setStatusService(true);
        schedule.setTanggalServiceAktual(LocalDate.now());

        repository.save(schedule);

        return ResponseEntity.ok("Service completed successfully");
    }

    @GetMapping("/pending")
    public ResponseEntity<List<ServiceScheduleResponse>> getPendingServices() {

        List<ServiceScheduleResponse> result =
                repository.findByStatusService(false)
                        .stream()
                        .map(s -> new ServiceScheduleResponse(
                                s.getId(),
                                s.getJadwalService(),
                                s.getServiceType(),
                                s.getStatusService()
                        ))
                        .toList();

        return ResponseEntity.ok(result);
    }

    @GetMapping("/completed")
    public ResponseEntity<List<ServiceScheduleResponse>> getCompletedServices() {

        List<ServiceScheduleResponse> result =
                repository.findByStatusService(true)
                        .stream()
                        .map(s -> new ServiceScheduleResponse(
                                s.getId(),
                                s.getJadwalService(),
                                s.getServiceType(),
                                s.getStatusService()
                        ))
                        .toList();

        return ResponseEntity.ok(result);
    }

}
