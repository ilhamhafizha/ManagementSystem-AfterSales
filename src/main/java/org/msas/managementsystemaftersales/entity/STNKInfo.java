package org.msas.managementsystemaftersales.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "stnk_info")
public class STNKInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate tglStnkTerbit;
    private LocalDate tglStnkBerlaku;
}
