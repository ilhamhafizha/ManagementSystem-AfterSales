package org.msas.managementsystemaftersales.repository;
import org.msas.managementsystemaftersales.entity.BastDocument;
import org.msas.managementsystemaftersales.model.enums.BastStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BastDocumentRepository extends JpaRepository<BastDocument, Long> {

    List<BastDocument> findByStatusBAST(BastStatus status);
}

