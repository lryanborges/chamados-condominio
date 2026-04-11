package com.dunnas.chamados_condominio.application.gateways;

import com.dunnas.chamados_condominio.domain.entity.CallType;

public interface CallTypeGateway {
    CallType createCallType(CallType callType);
}
