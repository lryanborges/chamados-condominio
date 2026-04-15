package com.dunnas.chamados_condominio.infrastructure.gateways.call;

import com.dunnas.chamados_condominio.application.gateways.CallGateway;
import com.dunnas.chamados_condominio.domain.entity.Call;
import com.dunnas.chamados_condominio.infrastructure.persistence.call.CallEntity;
import com.dunnas.chamados_condominio.infrastructure.persistence.call.CallRepository;

import java.util.List;

public class CallRepositoryGateway implements CallGateway {
    private final CallRepository repository;
    private final CallEntityMapper mapper;

    public CallRepositoryGateway(CallRepository repository, CallEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Call createCall(Call call) {
        CallEntity callEntity = mapper.toEntity(call);
        CallEntity savedCall = repository.save(callEntity);
        return mapper.toDomainObj(savedCall);
    }

    @Override
    public List<Call> findAllCallByFilters(Long statusId, List<Long> unitIds, String callType) {
        return repository.findAllByFilters(statusId, unitIds, callType)
                .stream()
                .map(mapper::toDomainObj)
                .toList();
    }

    @Override
    public Call findCallById(Long id) {
        CallEntity foundedCall = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Call not found"));

        return mapper.toDomainObj(foundedCall);
    }

    @Override
    public Call updateCall(Call call) {
        CallEntity callEntity = mapper.toEntity(call);
        CallEntity savedCall = repository.save(callEntity);
        return mapper.toDomainObj(savedCall);
    }
}
