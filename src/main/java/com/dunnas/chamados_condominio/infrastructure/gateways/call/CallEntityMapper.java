package com.dunnas.chamados_condominio.infrastructure.gateways.call;

import com.dunnas.chamados_condominio.domain.entity.Call;
import com.dunnas.chamados_condominio.infrastructure.persistence.call.CallEntity;

public class CallEntityMapper {
    CallEntity toEntity(Call call) {
        CallEntity callEntity = new CallEntity(call.getTitle(), call.getDescription(), call.getDeadline(), call.getUserId(), call.getUnitId(), call.getStatusId(), call.getCallTypeId());
        callEntity.setCreatedAt(call.getCreatedAt());
        callEntity.setFinishedAt(call.getFinishedAt());
        callEntity.setId(call.getId());
        return callEntity;
    }

    Call toDomainObj(CallEntity callEntity) {
        Call call = new Call(callEntity.getTitle(), callEntity.getDescription(), callEntity.getUnitId(), callEntity.getCallTypeId());
        call.setId(callEntity.getId());
        call.setStatusId(callEntity.getStatusId());
        call.setUserId(callEntity.getUserId());
        call.setCreatedAt(callEntity.getCreatedAt());
        call.setDeadline(callEntity.getDeadline());
        call.setFinishedAt(callEntity.getFinishedAt());
        return call;
    }
}
