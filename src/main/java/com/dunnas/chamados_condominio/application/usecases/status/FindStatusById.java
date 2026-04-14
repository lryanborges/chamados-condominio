package com.dunnas.chamados_condominio.application.usecases.status;

import com.dunnas.chamados_condominio.application.gateways.StatusGateway;
import com.dunnas.chamados_condominio.domain.entity.Status;

public class FindStatusById {
    private final StatusGateway statusGateway;

    public FindStatusById(StatusGateway statusGateway) {
        this.statusGateway = statusGateway;
    }

    public Status findStatusById(Long id) {
        return statusGateway.findStatusById(id);
    }
}
