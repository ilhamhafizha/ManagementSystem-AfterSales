package org.msas.managementsystemaftersales.service;


import lombok.RequiredArgsConstructor;
import org.msas.managementsystemaftersales.entity.BastDocument;
import org.msas.managementsystemaftersales.entity.ValidationLog;
import org.msas.managementsystemaftersales.model.enums.BastStatus;
import org.msas.managementsystemaftersales.repository.BastDocumentRepository;
import org.msas.managementsystemaftersales.repository.ValidationLogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class BastService {

    private final BastDocumentRepository bastDocumentRepository;
    private final ValidationLogRepository validationLogRepository;
    private final ServiceScheduleService serviceScheduleService;

    @Transactional
    public BastDocument validateBast(BastDocument bastDocument) {

        boolean isIncomplete = false;

        if (bastDocument.getVehicle() == null) {
            isIncomplete = true;
            saveValidationLog(bastDocument, "Vehicle data missing", "vehicle");
        } else {

            if (bastDocument.getVehicle().getNomorPolisi() == null) {
                isIncomplete = true;
                saveValidationLog(bastDocument, "Nomor polisi belum diisi", "nomorPolisi");
            }

            if (bastDocument.getVehicle().getNomorRangka() == null) {
                isIncomplete = true;
                saveValidationLog(bastDocument, "Nomor rangka belum diisi", "nomorRangka");
            }

            if (bastDocument.getVehicle().getNomorMesin() == null) {
                isIncomplete = true;
                saveValidationLog(bastDocument, "Nomor mesin belum diisi", "nomorMesin");
            }
        }

        if (isIncomplete) {
            bastDocument.setStatusBAST(BastStatus.INCOMPLETE);
        } else {
            bastDocument.setStatusBAST(BastStatus.VALIDATED);
            bastDocument.setTglValidasi(LocalDate.now());

            serviceScheduleService.generateInitialServiceSchedule(bastDocument);
        }


        return bastDocumentRepository.save(bastDocument);
    }

    private void saveValidationLog(
            BastDocument bastDocument,
            String message,
            String missingField
    ) {
        ValidationLog log = ValidationLog.builder()
                .bastDocument(bastDocument)
                .message(message)
                .missingField(missingField)
                .tglLog(LocalDate.now())
                .build();

        validationLogRepository.save(log);
    }
}

