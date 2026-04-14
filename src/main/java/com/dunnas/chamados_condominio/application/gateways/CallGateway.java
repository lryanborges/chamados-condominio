package com.dunnas.chamados_condominio.application.gateways;

import com.dunnas.chamados_condominio.domain.entity.Call;

import java.util.List;

public interface CallGateway {
    Call createCall(Call call);
    List<Call> findAllCallByFilters(Long statusId, Long unitId, String callType);
    Call findCallById(Long id);
}
