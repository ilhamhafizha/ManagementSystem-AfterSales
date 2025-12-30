package org.msas.managementsystemaftersales.repository;

import org.msas.managementsystemaftersales.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Optional<Vehicle> findByNomorRangka(String nomorRangka);

    Optional<Vehicle> findByNomorMesin(String nomorMesin);

    Optional<Vehicle> findByNomorPolisi(String nomorPolisi);
}

