package com.dunnas.chamados_condominio.application.usecases.status;

import com.dunnas.chamados_condominio.application.gateways.StatusGateway;
import com.dunnas.chamados_condominio.domain.entity.Status;

import java.util.List;

public class FindAllStatus {
    private StatusGateway statusGateway;

    public FindAllStatus(StatusGateway statusGateway) {
        this.statusGateway = statusGateway;
    }

    public List<Status> findAllStatus() {
        return statusGateway.findAllStatus();
    }
}
