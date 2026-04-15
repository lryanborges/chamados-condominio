package com.dunnas.chamados_condominio.infrastructure.gateways.unit;

import com.dunnas.chamados_condominio.domain.entity.Unit;
import com.dunnas.chamados_condominio.domain.entity.User;
import com.dunnas.chamados_condominio.infrastructure.gateways.user.UserEntityMapper;
import com.dunnas.chamados_condominio.infrastructure.persistence.unit.UnitEntity;

import java.util.List;

public class UnitEntityMapper {
    private final UserEntityMapper userEntityMapper;

    public UnitEntityMapper(UserEntityMapper userEntityMapper) {
        this.userEntityMapper = userEntityMapper;
    }

    public UnitEntity toEntity(Unit unitDomainObj) {
        UnitEntity unitEntity = new UnitEntity(unitDomainObj.getBlockId(), unitDomainObj.getFloor(), unitDomainObj.getIdentifier());
        unitEntity.setId(unitDomainObj.getId());
        return unitEntity;
    }

    public Unit toDomainObj(UnitEntity unitEntity) {
        Unit unit = new Unit(unitEntity.getBlockId(), unitEntity.getFloor(), unitEntity.getIdentifier());
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
