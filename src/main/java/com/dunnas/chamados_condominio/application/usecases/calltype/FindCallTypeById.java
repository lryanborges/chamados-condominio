package com.dunnas.chamados_condominio.application.usecases.calltype;

import com.dunnas.chamados_condominio.application.exceptions.BadRequestException;
import com.dunnas.chamados_condominio.application.exceptions.NotFoundException;
import com.dunnas.chamados_condominio.application.gateways.CallTypeGateway;
import com.dunnas.chamados_condominio.domain.entity.CallType;

public class FindCallTypeById {
    private final CallTypeGateway callTypeGateway;

    public FindCallTypeById(CallTypeGateway callTypeGateway) {
        this.callTypeGateway = callTypeGateway;
    }

    public CallType findCallTypeById(Long id) {
        if (id == null) {
            throw new BadRequestException("CallType id must not be null");
        }
        CallType callType = callTypeGateway.findCallTypeById(id);

        if (callType == null) {
            throw new NotFoundException("CallType not found");
        }

        return callType;
    }
}
