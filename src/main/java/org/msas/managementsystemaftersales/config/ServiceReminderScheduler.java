package org.msas.managementsystemaftersales.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.msas.managementsystemaftersales.entity.ServiceReminderLog;
import org.msas.managementsystemaftersales.entity.ServiceSchedule;
import org.msas.managementsystemaftersales.repository.ServiceReminderLogRepository;
import org.msas.managementsystemaftersales.repository.ServiceScheduleRepository;
import org.msas.managementsystemaftersales.service.WhatsappService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ServiceReminderScheduler {

    private final ServiceScheduleRepository serviceScheduleRepository;
    private final ServiceReminderLogRepository reminderLogRepository;
    private final WhatsappService whatsappService;

    private String buildMessage(ServiceSchedule s, String reminderType) {
        return String.format(
                "🔔 Reminder Service %s\n\n" +
                        "Vehicle ID: %d\n" +
                        "Service Type: %s\n" +
                        "Jadwal Service: %s\n\n" +
                        "Mohon segera lakukan service tepat waktu 🙏",
                reminderType,
                s.getVehicle().getId(),
                s.getServiceType(),
                s.getJadwalService()
        );
    }


    @Scheduled(cron = "0 * * * * *") // test tiap menit
    public void checkUpcomingServices() {

        LocalDate today = LocalDate.now();

        List<ServiceSchedule> schedules =
                serviceScheduleRepository.findByStatusService(false);

        schedules.forEach(s -> {

            LocalDate jadwal = s.getJadwalService();
            String reminderType = null;

//            if (jadwal.isEqual(today.plusDays(7))) {
//                reminderType = "H7";
//            } else if (jadwal.isEqual(today.plusDays(14))) {
//                reminderType = "H14";
//            }

            // ⚠️ TEST MODE
            if (jadwal.isEqual(today)) {
                reminderType = "H7";
            }


            if (reminderType == null) return;

            // ⛔ CEK DUPLIKASI
            boolean alreadySent =
                    reminderLogRepository.existsByServiceScheduleIdAndReminderType(
                            s.getId(),
                            reminderType
                    );

            if (alreadySent) {
                log.info(
                        "Reminder {} already sent | serviceScheduleId={}",
                        reminderType, s.getId()
                );
                return;
            }

            // 🔔 SIMULASI KIRIM REMINDER
//            log.info(
//                    "🔔 REMINDER {} | vehicleId={} | serviceType={} | jadwal={}",
//                    reminderType,
//                    s.getVehicle().getId(),
//                    s.getServiceType(),
//                    s.getJadwalService()
//            );

//            String phoneNumber = "620000000"; // TESTING DULU
//            String message = buildMessage(s, reminderType);
//
//            whatsappService.sendMessage(phoneNumber, message);
//
//
//            // ✅ SIMPAN LOG
//            reminderLogRepository.save(
//                    ServiceReminderLog.builder()
//                            .serviceScheduleId(s.getId())
//                            .vehicleId(s.getVehicle().getId())
//                            .reminderType(reminderType)
//                            .sentAt(LocalDateTime.now())
//                            .build()
//            );
//        });

            String phoneNumber = "6281283486983"; // ambil dari DB nanti
            String message = buildMessage(s, reminderType);

            boolean waSent = whatsappService.sendMessage(phoneNumber, message);

            reminderLogRepository.save(
                    ServiceReminderLog.builder()
                            .serviceScheduleId(s.getId())
                            .vehicleId(s.getVehicle().getId())
                            .reminderType(reminderType)
                            .waStatus(waSent ? "SENT" : "FAILED")
                            .sentAt(LocalDateTime.now())
                            .build()
            );
        });

    }
}
