package org.msas.managementsystemaftersales.controller;

import lombok.RequiredArgsConstructor;
import org.msas.managementsystemaftersales.dto.ServiceScheduleResponse;
import org.msas.managementsystemaftersales.entity.ServiceSchedule;
import org.msas.managementsystemaftersales.repository.ServiceScheduleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api/vehicles")
@RequiredArgsConstructor
public class VehicleServiceScheduleController {

    private final ServiceScheduleRepository repository;

    @GetMapping("/{vehicleId}/service-schedules")
    public ResponseEntity<List<ServiceScheduleResponse>> getSchedules(
            @PathVariable Long vehicleId) {

        List<ServiceScheduleResponse> response =
                repository.findByVehicleId(vehicleId)
                        .stream()
                        .map(s -> new ServiceScheduleResponse(
                                s.getId(),
                                s.getJadwalService(),
                                s.getServiceType(),
                                s.getStatusService()
                        ))
                        .toList();

        return ResponseEntity.ok(response);
    }
}
