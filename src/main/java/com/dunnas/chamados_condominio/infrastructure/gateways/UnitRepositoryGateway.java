package com.dunnas.chamados_condominio.infrastructure.gateways;

import com.dunnas.chamados_condominio.application.gateways.UnitGateway;
import com.dunnas.chamados_condominio.domain.entity.Unit;
import com.dunnas.chamados_condominio.infrastructure.persistence.UnitEntity;
import com.dunnas.chamados_condominio.infrastructure.persistence.UnitRepository;

public class UnitRepositoryGateway implements UnitGateway {

    private final UnitRepository repository;
    private final UnitEntityMapper mapper;

    public UnitRepositoryGateway(UnitRepository repository, UnitEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Unit createUnit(Unit newUnit) {
        UnitEntity unitEntity = mapper.toEntity(newUnit);
        UnitEntity savedUnit = repository.save(unitEntity);
        return new Unit(savedUnit.getBlockId(), savedUnit.getFloor(), savedUnit.getIdentifier());
    }
}
