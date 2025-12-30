package org.msas.managementsystemaftersales.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "partners")
public class Partner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String namaMitra;
}

