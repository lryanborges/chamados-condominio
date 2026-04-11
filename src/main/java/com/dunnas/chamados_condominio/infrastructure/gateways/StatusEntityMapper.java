package com.dunnas.chamados_condominio.infrastructure.gateways;

import com.dunnas.chamados_condominio.domain.entity.Status;
import com.dunnas.chamados_condominio.infrastructure.persistence.StatusEntity;

public class StatusEntityMapper {
    StatusEntity toEntity(Status status) {
        return new StatusEntity(status.getName());
    }

    Status toDomainObj(StatusEntity statusEntity) {
        return new Status(statusEntity.getName());
    }
}
