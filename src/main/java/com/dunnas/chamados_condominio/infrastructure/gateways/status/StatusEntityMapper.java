package com.dunnas.chamados_condominio.infrastructure.gateways.status;

import com.dunnas.chamados_condominio.domain.entity.Status;
import com.dunnas.chamados_condominio.infrastructure.persistence.status.StatusEntity;

public class StatusEntityMapper {
    StatusEntity toEntity(Status status) {
        return new StatusEntity(status.getName(), status.isFinal());
    }

    Status toDomainObj(StatusEntity statusEntity) {
        return new Status(statusEntity.getName(), statusEntity.isFinal());
    }
}
