package com.dunnas.chamados_condominio.application.usecases.calltype;

import com.dunnas.chamados_condominio.application.gateways.CallTypeGateway;
import com.dunnas.chamados_condominio.domain.entity.CallType;

public class FindCallTypeById {
    private final CallTypeGateway callTypeGateway;

    public FindCallTypeById(CallTypeGateway callTypeGateway) {
        this.callTypeGateway = callTypeGateway;
    }

    public CallType findCallTypeById(Long id) {
        return callTypeGateway.findCallTypeById(id);
    }
}
