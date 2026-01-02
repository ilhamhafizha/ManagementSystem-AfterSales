package org.msas.managementsystemaftersales.config;

import lombok.extern.slf4j.Slf4j;
import org.msas.managementsystemaftersales.entity.ServiceSchedule;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationService {

    public void notifyOps(ServiceSchedule schedule) {
        log.info(
                "[NOTIFICATION] Vehicle ID: {} | Service Date: {} | Type: {}",
                schedule.getVehicle().getId(),
                schedule.getJadwalService(),
                schedule.getServiceType()
        );
    }
}
