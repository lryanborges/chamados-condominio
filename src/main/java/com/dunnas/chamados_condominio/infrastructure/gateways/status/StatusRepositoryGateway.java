package com.dunnas.chamados_condominio.infrastructure.gateways.status;

import com.dunnas.chamados_condominio.application.gateways.StatusGateway;
import com.dunnas.chamados_condominio.domain.entity.Status;
import com.dunnas.chamados_condominio.infrastructure.persistence.status.StatusEntity;
import com.dunnas.chamados_condominio.infrastructure.persistence.status.StatusRepository;

public class StatusRepositoryGateway implements StatusGateway {

    private final StatusRepository repository;
    private final StatusEntityMapper mapper;

    public StatusRepositoryGateway(StatusRepository repository, StatusEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Status createStatus(Status status) {
        StatusEntity statusEntity = mapper.toEntity(status);
        StatusEntity savedStatus = repository.save(statusEntity);
        return mapper.toDomainObj(savedStatus);
    }

    @Override
    public Status findStatusById(Long id) {
        return repository.findById(id)
                .map(mapper::toDomainObj)
                .orElseThrow(() -> new RuntimeException("Status não encontrado"));
    }
}
