package com.dunnas.chamados_condominio.infrastructure.controllers;

import com.dunnas.chamados_condominio.domain.entity.Status;

public class StatusDTOMapper {
    CreateStatusResponse toResponse(Status status) {
        return new CreateStatusResponse(status.getName());
    }

    Status toEntity(CreateStatusRequest createStatusRequest) {
        return new Status(createStatusRequest.name());
    }
}
