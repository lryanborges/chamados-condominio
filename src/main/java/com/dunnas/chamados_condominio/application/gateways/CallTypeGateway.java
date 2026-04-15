package com.dunnas.chamados_condominio.application.gateways;

import com.dunnas.chamados_condominio.domain.entity.CallType;

import java.util.List;

public interface CallTypeGateway {
    CallType createCallType(CallType callType);
    CallType findCallTypeById(Long id);
    List<CallType> findAllCallTypes();
}
