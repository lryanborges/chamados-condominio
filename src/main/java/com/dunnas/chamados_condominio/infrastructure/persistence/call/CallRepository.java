package com.dunnas.chamados_condominio.infrastructure.persistence.call;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CallRepository extends JpaRepository<CallEntity, Long> {
}
