package com.dunnas.chamados_condominio.infrastructure.gateways.unit;

import com.dunnas.chamados_condominio.domain.entity.Block;
import com.dunnas.chamados_condominio.domain.entity.Unit;
import com.dunnas.chamados_condominio.domain.entity.User;
import com.dunnas.chamados_condominio.infrastructure.gateways.block.BlockEntityMapper;
import com.dunnas.chamados_condominio.infrastructure.gateways.user.UserEntityMapper;
import com.dunnas.chamados_condominio.infrastructure.persistence.block.BlockEntity;
import com.dunnas.chamados_condominio.infrastructure.persistence.unit.UnitEntity;

import java.util.List;

public class UnitEntityMapper {
    private final UserEntityMapper userEntityMapper;
    private final BlockEntityMapper blockEntityMapper;

    public UnitEntityMapper(UserEntityMapper userEntityMapper, BlockEntityMapper blockEntityMapper) {
        this.userEntityMapper = userEntityMapper;
        this.blockEntityMapper = blockEntityMapper;
    }

    public UnitEntity toEntity(Unit unitDomainObj) {
        BlockEntity blockEntity = blockEntityMapper.toEntity(unitDomainObj.getBlock());
        UnitEntity unitEntity = new UnitEntity(blockEntity, unitDomainObj.getFloor(), unitDomainObj.getIdentifier());
        unitEntity.setId(unitDomainObj.getId());
        return unitEntity;
    }

    public Unit toDomainObj(UnitEntity unitEntity) {
        Block block = blockEntityMapper.toDomainObj(unitEntity.getBlock());
        Unit unit = new Unit(block, unitEntity.getFloor(), unitEntity.getIdentifier());
        unit.setId(unitEntity.getId());

        if (unitEntity.getUsers() != null && !unitEntity.getUsers().isEmpty()) {
            unit.setUsers(userEntityMapper.toDomainObjList(unitEntity.getUsers()));
        }

        return unit;
    }

    public List<Unit> toDomainObjList(List<UnitEntity> unitEntityList) {
        return unitEntityList.stream()
                .map(this::toDomainObj)
                .toList();
    }

    public List<UnitEntity> toEntityList(List<Unit> unitList) {
        return unitList.stream()
                .map(this::toEntity)
                .toList();
    }
}
