package com.dunnas.chamados_condominio.infrastructure.gateways.unit;

import com.dunnas.chamados_condominio.application.gateways.UnitGateway;
import com.dunnas.chamados_condominio.domain.entity.Block;
import com.dunnas.chamados_condominio.domain.entity.Unit;
import com.dunnas.chamados_condominio.infrastructure.gateways.block.BlockEntityMapper;
import com.dunnas.chamados_condominio.infrastructure.persistence.unit.UnitEntity;
import com.dunnas.chamados_condominio.infrastructure.persistence.unit.UnitRepository;

import java.util.List;

public class UnitRepositoryGateway implements UnitGateway {

    private final UnitRepository repository;
    private final UnitEntityMapper mapper;
    private final BlockEntityMapper blockEntityMapper;

    public UnitRepositoryGateway(UnitRepository repository, UnitEntityMapper mapper, BlockEntityMapper blockEntityMapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.blockEntityMapper = blockEntityMapper;
    }

    @Override
    public Unit createUnit(Unit newUnit) {
        UnitEntity unitEntity = mapper.toEntity(newUnit);
        UnitEntity savedUnit = repository.save(unitEntity);
        Block block = blockEntityMapper.toDomainObj(savedUnit.getBlock());
        return new Unit(block, savedUnit.getFloor(), savedUnit.getIdentifier());
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
