package org.msas.managementsystemaftersales.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.msas.managementsystemaftersales.entity.BastDocument;
import org.msas.managementsystemaftersales.entity.ServiceSchedule;
import org.msas.managementsystemaftersales.model.enums.BastStatus;
import org.msas.managementsystemaftersales.repository.ServiceScheduleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ServiceScheduleService {

    private final ServiceScheduleRepository serviceScheduleRepository;

    @Transactional
    public void generateInitialServiceSchedule(BastDocument bastDocument) {

        if (bastDocument.getStatusBAST() != BastStatus.VALIDATED) {
            return; // safety check
        }

        LocalDate baseDate = bastDocument.getTglValidasi();

        // Service pertama (3 bulan)
        ServiceSchedule firstService = ServiceSchedule.builder()
                .vehicle(bastDocument.getVehicle())
                .jadwalServicePertama(baseDate.plusMonths(3))
                .jadwalServiceRutin(baseDate.plusMonths(3))
                .statusService(false)
                .build();

        // Service rutin berikutnya (6 bulan)
        ServiceSchedule routineService = ServiceSchedule.builder()
                .vehicle(bastDocument.getVehicle())
                .jadwalServiceRutin(baseDate.plusMonths(6))
                .statusService(false)
                .build();

        serviceScheduleRepository.save(firstService);
        serviceScheduleRepository.save(routineService);
    }
}
