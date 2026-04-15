package com.dunnas.chamados_condominio.infrastructure.persistence.unit;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UnitRepository extends JpaRepository<UnitEntity, Long> {
    List<UnitEntity> findByBlockId(Long blockId);
    List<UnitEntity> findByUserId(Long userId);
}
