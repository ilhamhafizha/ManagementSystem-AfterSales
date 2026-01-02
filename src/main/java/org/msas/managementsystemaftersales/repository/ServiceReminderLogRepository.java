package org.msas.managementsystemaftersales.repository;


import org.msas.managementsystemaftersales.entity.ServiceReminderLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ServiceReminderLogRepository
        extends JpaRepository<ServiceReminderLog, Long> {

    // ✅ UNTUK ENDPOINT /today
    List<ServiceReminderLog> findBySentAtBetween(
            LocalDateTime start,
            LocalDateTime end
    );

    // (opsional, sudah kamu pakai di scheduler)
    boolean existsByServiceScheduleIdAndReminderType(
            Long serviceScheduleId,
            String reminderType
    );
}