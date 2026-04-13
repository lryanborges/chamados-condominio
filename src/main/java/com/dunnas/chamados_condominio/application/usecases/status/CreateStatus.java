package com.dunnas.chamados_condominio.application.usecases.status;

import com.dunnas.chamados_condominio.application.gateways.StatusGateway;
import com.dunnas.chamados_condominio.domain.entity.Status;

public class CreateStatus {
    private final StatusGateway statusGateway;

    public CreateStatus(final StatusGateway statusGateway) {
        this.statusGateway = statusGateway;
    }

    public Status createStatus(Status status) {
        return statusGateway.createStatus(status);
    }
}
