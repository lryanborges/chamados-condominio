package com.dunnas.chamados_condominio.application.usecases.call;

import com.dunnas.chamados_condominio.application.exceptions.BadRequestException;
import com.dunnas.chamados_condominio.application.exceptions.NotFoundException;
import com.dunnas.chamados_condominio.application.gateways.CallGateway;
import com.dunnas.chamados_condominio.domain.entity.Call;

public class FindCallById {
    private final CallGateway callGateway;

    public FindCallById(CallGateway callGateway) {
        this.callGateway = callGateway;
    }

    public Call findCallById(Long id) {
        if (id == null) {
            throw new BadRequestException("Call id must not be null");
        }

        Call call = callGateway.findCallById(id);

        if (call == null) {
            throw new NotFoundException("Call not found");
        }

        return call;
    }
}
