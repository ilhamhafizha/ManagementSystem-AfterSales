package org.msas.managementsystemaftersales.entity;

import jakarta.persistence.*;
import lombok.*;
import org.msas.managementsystemaftersales.model.enums.BastStatus;

import java.time.LocalDate;

@Entity
@Table(name = "bast_documents")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BastDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long documentId;

    @Enumerated(EnumType.STRING)
    private BastStatus statusBAST;

    private LocalDate tglValidasi;

    @OneToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;
}

