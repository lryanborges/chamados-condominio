package com.dunnas.chamados_condominio.application.usecases.status;

import com.dunnas.chamados_condominio.application.exceptions.BadRequestException;
import com.dunnas.chamados_condominio.application.exceptions.NotFoundException;
import com.dunnas.chamados_condominio.application.gateways.StatusGateway;
import com.dunnas.chamados_condominio.domain.entity.Status;

public class FindStatusById {
    private final StatusGateway statusGateway;

    public FindStatusById(StatusGateway statusGateway) {
        this.statusGateway = statusGateway;
    }

    public Status findStatusById(Long id) {
        if (id == null) {
            throw new BadRequestException("Status id must not be null");
        }
        Status status = statusGateway.findStatusById(id);

        if (status == null) {
            throw new NotFoundException("Status not found");
        }

        return status;
    }
}
