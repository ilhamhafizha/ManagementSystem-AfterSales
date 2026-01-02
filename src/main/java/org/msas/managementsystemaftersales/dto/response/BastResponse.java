package org.msas.managementsystemaftersales.dto.response;

import lombok.Builder;
import lombok.Data;
import org.msas.managementsystemaftersales.model.enums.BastStatus;

import java.time.LocalDate;

@Data
@Builder
public class BastResponse {

    private Long bastId;
    private BastStatus status;
    private LocalDate tglValidasi;
    private Long vehicleId;
}
