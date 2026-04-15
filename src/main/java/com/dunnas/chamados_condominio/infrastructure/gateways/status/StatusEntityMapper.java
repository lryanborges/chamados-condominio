package com.dunnas.chamados_condominio.infrastructure.gateways.status;

import com.dunnas.chamados_condominio.domain.entity.Status;
import com.dunnas.chamados_condominio.infrastructure.persistence.status.StatusEntity;

import java.util.List;

public class StatusEntityMapper {
    public StatusEntity toEntity(Status status) {
        return new StatusEntity(status.getName(), status.getIsFinal());
    }

    public Status toDomainObj(StatusEntity statusEntity) {
        return new Status(statusEntity.getName(), statusEntity.isFinal());
    }

    public List<Status> toDomainObjList(List<StatusEntity> statusEntityList) {
        return statusEntityList.stream()
                .map(this::toDomainObj)
                .toList();
    }

    public List<StatusEntity> toEntityList(List<Status> statusList) {
        return statusList.stream()
                .map(this::toEntity)
                .toList();
    }
}
