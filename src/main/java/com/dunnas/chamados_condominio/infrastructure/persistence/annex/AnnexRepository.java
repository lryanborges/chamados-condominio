package com.dunnas.chamados_condominio.infrastructure.persistence.annex;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnnexRepository extends JpaRepository<AnnexEntity, Long> {
    List<AnnexEntity> findAllByCallId(Long callId);
}
