package com.dunnas.chamados_condominio.infrastructure.gateways.call;

import com.dunnas.chamados_condominio.domain.entity.*;
import com.dunnas.chamados_condominio.infrastructure.gateways.calltype.CallTypeEntityMapper;
import com.dunnas.chamados_condominio.infrastructure.gateways.status.StatusEntityMapper;
import com.dunnas.chamados_condominio.infrastructure.gateways.unit.UnitEntityMapper;
import com.dunnas.chamados_condominio.infrastructure.gateways.user.UserEntityMapper;
import com.dunnas.chamados_condominio.infrastructure.persistence.call.CallEntity;
import com.dunnas.chamados_condominio.infrastructure.persistence.calltype.CallTypeEntity;
import com.dunnas.chamados_condominio.infrastructure.persistence.status.StatusEntity;
import com.dunnas.chamados_condominio.infrastructure.persistence.unit.UnitEntity;
import com.dunnas.chamados_condominio.infrastructure.persistence.user.UserEntity;

public class CallEntityMapper {
    private final UnitEntityMapper unitEntityMapper;
    private final UserEntityMapper userEntityMapper;
    private final StatusEntityMapper statusEntityMapper;
    private final CallTypeEntityMapper callTypeEntityMapper;

    public CallEntityMapper(UnitEntityMapper unitEntityMapper, UserEntityMapper userEntityMapper, StatusEntityMapper statusEntityMapper, CallTypeEntityMapper callTypeEntityMapper) {
        this.unitEntityMapper = unitEntityMapper;
        this.userEntityMapper = userEntityMapper;
        this.statusEntityMapper = statusEntityMapper;
        this.callTypeEntityMapper = callTypeEntityMapper;
    }

    public CallEntity toEntity(Call call) {
        UserEntity userEntity = userEntityMapper.toEntity(call.getUser());
        UnitEntity unitEntity = unitEntityMapper.toEntity(call.getUnit());
        StatusEntity statusEntity = statusEntityMapper.toEntity(call.getStatus());
        CallTypeEntity callTypeEntity = callTypeEntityMapper.toEntity(call.getCallType());

        CallEntity callEntity = new CallEntity(call.getTitle(), call.getDescription(), call.getDeadline(), userEntity, unitEntity, statusEntity, callTypeEntity);
        callEntity.setCreatedAt(call.getCreatedAt());
        callEntity.setFinishedAt(call.getFinishedAt());
        callEntity.setId(call.getId());
        return callEntity;
    }

    public Call toDomainObj(CallEntity callEntity) {
        User user = null;
        if(callEntity.getUser() != null) {
            user = userEntityMapper.toDomainObj(callEntity.getUser());
        } else {
            user = new User("Usuário Removido", "0@unexist.com", "", Role.RESIDENT, "");
        }
        Unit unit = unitEntityMapper.toDomainObj(callEntity.getUnit());
        Status status = statusEntityMapper.toDomainObj(callEntity.getStatus());
        CallType callType = callTypeEntityMapper.toDomainObj(callEntity.getCallType());

        Call call = new Call(callEntity.getTitle(), callEntity.getDescription(), unit, callType);
        call.setId(callEntity.getId());
        call.setStatus(status);
        call.setUser(user);
        call.setCreatedAt(callEntity.getCreatedAt());
        call.setDeadline(callEntity.getDeadline());
        call.setFinishedAt(callEntity.getFinishedAt());
        return call;
    }
}
