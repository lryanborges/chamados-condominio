package com.dunnas.chamados_condominio.infrastructure.gateways.calltype;

import com.dunnas.chamados_condominio.domain.entity.CallType;
import com.dunnas.chamados_condominio.infrastructure.persistence.calltype.CallTypeEntity;

import java.util.List;

public class CallTypeEntityMapper {
    public CallTypeEntity toEntity(CallType callType) {
        CallTypeEntity entity = new CallTypeEntity(callType.getTitle(), callType.getDeadline());
        entity.setId(callType.getId());
        return entity;
    }

    public CallType toDomainObj(CallTypeEntity callTypeEntity){
        CallType callType = new CallType(callTypeEntity.getTitle(), callTypeEntity.getDeadline());
        callType.setId(callTypeEntity.getId());
        return callType;
    }

    public List<CallType> toDomainObjList(List<CallTypeEntity> callTypeEntityList) {
        return callTypeEntityList.stream()
                .map(this::toDomainObj)
                .toList();
    }

    public List<CallTypeEntity> toEntityList(List<CallType> callTypeList) {
        return callTypeList.stream()
                .map(this::toEntity)
                .toList();
    }
}
