package org.msas.managementsystemaftersales.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.msas.managementsystemaftersales.entity.ServiceSchedule;
import org.msas.managementsystemaftersales.entity.ServiceType;
import org.msas.managementsystemaftersales.repository.ServiceScheduleRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ServiceMonitoringScheduler {

    private final ServiceScheduleRepository serviceScheduleRepository;
    private final NotificationService notificationService;

    // Jalan setiap hari jam 08:00 pagi
    @Scheduled(cron = "0 0 8 * * ?")
    public void monitorServiceSchedule() {

        LocalDate today = LocalDate.now();
        LocalDate reminderDate = today.plusDays(7);

        List<ServiceSchedule> schedules =
                serviceScheduleRepository.findByJadwalServiceBetweenAndServiceType(
                        today,
                        reminderDate,
                        ServiceType.ROUTINE
                );

        for (ServiceSchedule schedule : schedules) {
            if (!schedule.getStatusService()) {
                notificationService.notifyOps(schedule);
            }
        }

        log.info(
                "Service schedule monitoring completed. Total checked: {}",
                schedules.size()
        );
    }
}
