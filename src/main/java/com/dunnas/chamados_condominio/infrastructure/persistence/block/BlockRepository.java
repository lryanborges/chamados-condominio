package com.dunnas.chamados_condominio.infrastructure.persistence.block;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockRepository extends JpaRepository<BlockEntity, Long> {
}
