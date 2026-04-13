package com.dunnas.chamados_condominio.application.gateways;

import com.dunnas.chamados_condominio.domain.entity.Call;

public interface CallGateway {
    Call createCall(Call call);
}
