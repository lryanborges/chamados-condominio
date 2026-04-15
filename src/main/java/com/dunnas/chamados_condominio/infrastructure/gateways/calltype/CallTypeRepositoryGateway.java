package com.dunnas.chamados_condominio.infrastructure.gateways.calltype;

import com.dunnas.chamados_condominio.application.gateways.CallTypeGateway;
import com.dunnas.chamados_condominio.domain.entity.CallType;
import com.dunnas.chamados_condominio.infrastructure.persistence.calltype.CallTypeEntity;
import com.dunnas.chamados_condominio.infrastructure.persistence.calltype.CallTypeRepository;

import java.util.List;

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

    @Override
    public CallType findCallTypeById(Long id) {
        CallTypeEntity foundedCallType = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Call Type not found"));
        return mapper.toDomainObj(foundedCallType);
    }

    @Override
    public List<CallType> findAllCallTypes() {
        List<CallTypeEntity> entityList = repository.findAll();
        return mapper.toDomainObjList(entityList);
    }
}
