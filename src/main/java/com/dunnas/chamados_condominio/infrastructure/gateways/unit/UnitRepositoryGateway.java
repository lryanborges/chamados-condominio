package com.dunnas.chamados_condominio.infrastructure.gateways.unit;

import com.dunnas.chamados_condominio.application.gateways.UnitGateway;
import com.dunnas.chamados_condominio.domain.entity.Unit;
import com.dunnas.chamados_condominio.infrastructure.persistence.unit.UnitEntity;
import com.dunnas.chamados_condominio.infrastructure.persistence.unit.UnitRepository;

import java.util.List;

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

    @Override
    public Unit findUnitById(Long id) {
        UnitEntity unitEntity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Unit not found"));
        return mapper.toDomainObj(unitEntity);
    }

    @Override
    public List<Unit> findUnitByBlockId(Long blockId) {
        List<UnitEntity> unitEntities = repository.findByBlockId(blockId);
        return mapper.toDomainObjList(unitEntities);
    }

    @Override
    public List<Unit> findUnitByUserId(Long userId) {
        List<UnitEntity> unitEntities = repository.findAllByUserId(userId);
        return mapper.toDomainObjList(unitEntities);
    }


}
