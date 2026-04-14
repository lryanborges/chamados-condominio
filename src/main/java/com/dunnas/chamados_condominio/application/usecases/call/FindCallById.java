package com.dunnas.chamados_condominio.application.usecases.call;

import com.dunnas.chamados_condominio.application.gateways.CallGateway;
import com.dunnas.chamados_condominio.domain.entity.Call;

public class FindCallById {
    private final CallGateway callGateway;

    public FindCallById(CallGateway callGateway) {
        this.callGateway = callGateway;
    }

    public Call findCallById(Long id) {
        return callGateway.findCallById(id);
    }
}
