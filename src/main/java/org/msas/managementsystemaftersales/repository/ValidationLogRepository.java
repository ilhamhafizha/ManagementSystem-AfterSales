package org.msas.managementsystemaftersales.repository;

import org.msas.managementsystemaftersales.entity.ValidationLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ValidationLogRepository extends JpaRepository<ValidationLog, Long> {
}
