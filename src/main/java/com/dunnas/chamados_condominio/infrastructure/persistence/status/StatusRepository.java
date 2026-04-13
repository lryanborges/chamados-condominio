package com.dunnas.chamados_condominio.infrastructure.persistence.status;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<StatusEntity, Long> {
}
