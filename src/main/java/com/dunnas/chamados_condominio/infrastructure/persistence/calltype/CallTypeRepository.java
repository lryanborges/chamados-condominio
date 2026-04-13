package com.dunnas.chamados_condominio.infrastructure.persistence.calltype;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CallTypeRepository extends JpaRepository<CallTypeEntity, Long> {
}
