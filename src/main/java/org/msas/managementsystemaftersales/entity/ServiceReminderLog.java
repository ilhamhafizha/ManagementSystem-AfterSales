package org.msas.managementsystemaftersales.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "service_reminder_log")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceReminderLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long serviceScheduleId;

    private Long vehicleId;

    private String reminderType; // H7 atau H14

    private LocalDateTime sentAt;
}
