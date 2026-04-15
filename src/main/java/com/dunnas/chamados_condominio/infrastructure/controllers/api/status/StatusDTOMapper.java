package com.dunnas.chamados_condominio.infrastructure.controllers.api.status;

import com.dunnas.chamados_condominio.domain.entity.Status;

public class StatusDTOMapper {
    public CreateStatusResponse toResponse(Status status) {
        return new CreateStatusResponse(status.getName(), status.getIsFinal());
    }

    public Status toEntity(CreateStatusRequest request) {
        return new Status(request.name(), request.isFinal());
    }
}
