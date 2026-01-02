package org.msas.managementsystemaftersales.controller;

import lombok.RequiredArgsConstructor;
import org.msas.managementsystemaftersales.dto.response.ReminderResponse;
import org.msas.managementsystemaftersales.repository.ServiceReminderLogRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/reminders")
@RequiredArgsConstructor
public class ReminderController {

    private final ServiceReminderLogRepository repository;

    @GetMapping("/today")
    public ResponseEntity<List<ReminderResponse>> today() {

        LocalDateTime start = LocalDate.now().atStartOfDay();
        LocalDateTime end = LocalDate.now().atTime(23, 59, 59);

        List<ReminderResponse> result =
                repository.findBySentAtBetween(start, end)
                        .stream()
                        .map(r -> new ReminderResponse(
                                r.getVehicleId(),
                                r.getReminderType(),
                                r.getSentAt()
                        ))
                        .toList();

        return ResponseEntity.ok(result);
    }
}

