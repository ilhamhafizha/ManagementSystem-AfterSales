package org.msas.managementsystemaftersales.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.msas.managementsystemaftersales.dto.ServiceScheduleResponse;
import org.msas.managementsystemaftersales.entity.BastDocument;
import org.msas.managementsystemaftersales.entity.ServiceSchedule;
import org.msas.managementsystemaftersales.entity.ServiceType;
import org.msas.managementsystemaftersales.model.enums.BastStatus;
import org.msas.managementsystemaftersales.repository.ServiceScheduleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceScheduleService {

    private final ServiceScheduleRepository serviceScheduleRepository;

    @Transactional
    public void generateInitialServiceSchedule(BastDocument bastDocument) {

        if (bastDocument.getStatusBAST() != BastStatus.VALIDATED) {
            return;
        }

        Long vehicleId = bastDocument.getVehicle().getId();

        // ✅ STEP 2: CEK DULU
        if (serviceScheduleRepository.existsByVehicleId(vehicleId)) {
            return; // ⛔ stop, sudah pernah dibuat
        }

        LocalDate baseDate = bastDocument.getTglValidasi();

        ServiceSchedule firstService = ServiceSchedule.builder()
                .vehicle(bastDocument.getVehicle())
                .jadwalService(baseDate.plusMonths(3))
                .serviceType(ServiceType.FIRST)
                .statusService(false)
                .build();

        ServiceSchedule routineService = ServiceSchedule.builder()
                .vehicle(bastDocument.getVehicle())
                .jadwalService(baseDate.plusMonths(6))
                .serviceType(ServiceType.ROUTINE)
                .statusService(false)
                .build();

        serviceScheduleRepository.saveAll(
                List.of(firstService, routineService)
        );
    }

    public List<ServiceScheduleResponse> getAll() {
        return serviceScheduleRepository.findAll()
                .stream()
                .map(s -> new ServiceScheduleResponse(
                        s.getId(),
                        s.getJadwalService(),
                        s.getServiceType(),
                        s.getStatusService()
                ))
                .toList();
    }
}
