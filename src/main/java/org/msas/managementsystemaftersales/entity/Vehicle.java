package org.msas.managementsystemaftersales.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vehicles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String jenisMobil;
    private String lokasiPengiriman;

    private String warna;
    private String warnaByMitra;

    @Column(unique = true)
    private String nomorRangka;

    @Column(unique = true)
    private String nomorMesin;

    @Column(unique = true)
    private String nomorPolisi;
}
