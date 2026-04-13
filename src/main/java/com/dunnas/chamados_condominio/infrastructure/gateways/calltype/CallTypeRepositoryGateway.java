package com.dunnas.chamados_condominio.infrastructure.gateways.calltype;

import com.dunnas.chamados_condominio.application.gateways.CallTypeGateway;
import com.dunnas.chamados_condominio.domain.entity.CallType;
import com.dunnas.chamados_condominio.infrastructure.persistence.calltype.CallTypeEntity;
import com.dunnas.chamados_condominio.infrastructure.persistence.calltype.CallTypeRepository;

public class CallTypeRepositoryGateway implements CallTypeGateway {

    private final CallTypeRepository repository;
    private final CallTypeEntityMapper mapper;

    public CallTypeRepositoryGateway(CallTypeRepository repository, CallTypeEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public CallType createCallType(CallType callType) {
        CallTypeEntity callTypeEntity = mapper.toEntity(callType);
        CallTypeEntity savedCallType = repository.save(callTypeEntity);
        return mapper.toDomainObj(savedCallType);
    }
}
