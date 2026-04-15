package com.dunnas.chamados_condominio.infrastructure.gateways.status;

import com.dunnas.chamados_condominio.domain.entity.Status;
import com.dunnas.chamados_condominio.infrastructure.persistence.status.StatusEntity;

import java.util.List;

public class StatusEntityMapper {
    public StatusEntity toEntity(Status status) {
        StatusEntity statusEntity = new StatusEntity(status.getName(), status.getIsFinal());
        statusEntity.setId(status.getId());
        return statusEntity;
    }

    public Status toDomainObj(StatusEntity statusEntity) {
        Status status = new Status(statusEntity.getName(), statusEntity.getIsFinal());
        status.setId(statusEntity.getId());
        return status;
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
