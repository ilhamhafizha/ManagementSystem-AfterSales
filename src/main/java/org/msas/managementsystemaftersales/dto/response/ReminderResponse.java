package org.msas.managementsystemaftersales.dto.response;

import java.time.LocalDateTime;

public class ReminderResponse {

    private Long vehicleId;
    private String reminderType;
    private LocalDateTime sentAt;

    public ReminderResponse(
            Long vehicleId,
            String reminderType,
            LocalDateTime sentAt
    ) {
        this.vehicleId = vehicleId;
        this.reminderType = reminderType;
        this.sentAt = sentAt;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public String getReminderType() {
        return reminderType;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }
}