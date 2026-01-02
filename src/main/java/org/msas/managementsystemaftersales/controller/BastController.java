package org.msas.managementsystemaftersales.controller;

import lombok.RequiredArgsConstructor;
import org.msas.managementsystemaftersales.dto.request.BastCreateRequest;
import org.msas.managementsystemaftersales.dto.respon.BastResponse;
import org.msas.managementsystemaftersales.entity.BastDocument;
import org.msas.managementsystemaftersales.entity.Vehicle;
import org.msas.managementsystemaftersales.model.enums.BastStatus;
import org.msas.managementsystemaftersales.repository.BastDocumentRepository;
import org.msas.managementsystemaftersales.repository.VehicleRepository;
import org.msas.managementsystemaftersales.service.BastService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bast")
@RequiredArgsConstructor
public class BastController {

    private final BastService bastService;
    private final BastDocumentRepository bastRepository;
    private final VehicleRepository vehicleRepository;

    /**
     * 1️⃣ OPS input BAST
     */
    @PostMapping
    public ResponseEntity<BastResponse> createBast(
            @RequestBody BastCreateRequest request
    ) {

        // 1️⃣ CARI VEHICLE DULU
        Vehicle vehicle = vehicleRepository
                .findByNomorMesin(request.getNomorMesin())
                .orElseGet(() -> {
                    // 2️⃣ KALAU BELUM ADA, BARU CREATE
                    Vehicle newVehicle = Vehicle.builder()
                            .nomorPolisi(request.getNomorPolisi())
                            .nomorRangka(request.getNomorRangka())
                            .nomorMesin(request.getNomorMesin())
                            .jenisMobil(request.getJenisMobil())
                            .warna(request.getWarna())
                            .lokasiPengiriman(request.getLokasiPengiriman())
                            .build();

                    return vehicleRepository.save(newVehicle);
                });

        // 3️⃣ BUAT BAST
        BastDocument bast = BastDocument.builder()
                .vehicle(vehicle)
                .statusBAST(BastStatus.RECEIVED)
                .build();

        BastDocument saved = bastRepository.save(bast);

        return ResponseEntity.ok(toResponse(saved));
    }

    /**
     * 2️⃣ OPS trigger validasi BAST
     */
    @PostMapping("/{id}/validate")
    public ResponseEntity<BastResponse> validateBast(
            @PathVariable Long id
    ) {

        BastDocument bast = bastRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("BAST not found"));

        BastDocument validated = bastService.validateBast(bast);

        return ResponseEntity.ok(toResponse(validated));
    }

    private BastResponse toResponse(BastDocument bast) {
        return BastResponse.builder()
                .bastId(bast.getDocumentId())
                .status(bast.getStatusBAST())
                .tglValidasi(bast.getTglValidasi())
                .vehicleId(
                        bast.getVehicle() != null
                                ? bast.getVehicle().getId()
                                : null
                )
                .build();
    }
}
