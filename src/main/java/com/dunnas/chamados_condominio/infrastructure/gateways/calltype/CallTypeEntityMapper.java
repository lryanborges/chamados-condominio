package com.dunnas.chamados_condominio.infrastructure.gateways.calltype;

import com.dunnas.chamados_condominio.domain.entity.CallType;
import com.dunnas.chamados_condominio.infrastructure.persistence.calltype.CallTypeEntity;

public class CallTypeEntityMapper {
    CallTypeEntity toEntity(CallType callType) {
        CallTypeEntity entity = new CallTypeEntity(callType.getTitle(), callType.getDeadline());
        entity.setId(callType.getId());
        return entity;
    }

    CallType toDomainObj(CallTypeEntity callTypeEntity){
        CallType callType = new CallType(callTypeEntity.getTitle(), callTypeEntity.getDeadline());
        callType.setId(callTypeEntity.getId());
        return callType;
    }
}
