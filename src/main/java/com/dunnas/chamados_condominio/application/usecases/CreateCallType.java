package com.dunnas.chamados_condominio.application.usecases;

import com.dunnas.chamados_condominio.application.gateways.CallTypeGateway;
import com.dunnas.chamados_condominio.domain.entity.CallType;

public class CreateCallType {
    private final CallTypeGateway callTypeGateway;

    public CreateCallType(CallTypeGateway callTypeGateway) {
        this.callTypeGateway = callTypeGateway;
    }

    public CallType createCallType(CallType callType) {
        return callTypeGateway.createCallType(callType);
    }
}
