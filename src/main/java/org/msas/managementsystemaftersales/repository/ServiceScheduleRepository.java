package org.msas.managementsystemaftersales.repository;

import org.msas.managementsystemaftersales.entity.ServiceSchedule;
import org.msas.managementsystemaftersales.entity.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ServiceScheduleRepository
        extends JpaRepository<ServiceSchedule, Long> {

    // untuk scheduler
    List<ServiceSchedule> findByJadwalServiceBetweenAndServiceType(
            LocalDate start,
            LocalDate end,
            ServiceType serviceType
    );

    // untuk endpoint vehicle
    List<ServiceSchedule> findByVehicleId(Long vehicleId);
    boolean existsByVehicleId(Long vehicleId);
}

