package com.dunnas.chamados_condominio.application.usecases.calltype;

import com.dunnas.chamados_condominio.application.gateways.CallTypeGateway;
import com.dunnas.chamados_condominio.domain.entity.CallType;

import java.util.List;

public class FindAllCallTypes {
    private final CallTypeGateway callTypeGateway;

    public FindAllCallTypes(CallTypeGateway callTypeGateway) {
        this.callTypeGateway = callTypeGateway;
    }

    public List<CallType> findAllCallTypes() {
        return callTypeGateway.findAllCallTypes();
    }
}
