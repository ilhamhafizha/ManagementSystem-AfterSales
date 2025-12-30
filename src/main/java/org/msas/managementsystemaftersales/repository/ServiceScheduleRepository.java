package org.msas.managementsystemaftersales.repository;
import org.msas.managementsystemaftersales.entity.ServiceSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ServiceScheduleRepository extends JpaRepository<ServiceSchedule, Long> {

    List<ServiceSchedule> findByJadwalServiceRutinBetween(
            LocalDate start,
            LocalDate end
    );
}