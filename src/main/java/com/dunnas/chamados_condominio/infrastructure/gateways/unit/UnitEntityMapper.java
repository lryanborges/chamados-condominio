package com.dunnas.chamados_condominio.infrastructure.gateways.unit;

import com.dunnas.chamados_condominio.domain.entity.Unit;
import com.dunnas.chamados_condominio.infrastructure.persistence.unit.UnitEntity;

import java.util.List;

public class UnitEntityMapper {
    public UnitEntity toEntity(Unit unitDomainObj) {
        UnitEntity unitEntity = new UnitEntity(unitDomainObj.getBlockId(), unitDomainObj.getFloor(), unitDomainObj.getIdentifier());
        unitEntity.setId(unitDomainObj.getId());
        return unitEntity;
    }

    public Unit toDomainObj(UnitEntity unitEntity) {
        Unit unit = new Unit(unitEntity.getBlockId(), unitEntity.getFloor(), unitEntity.getIdentifier());
        unit.setId(unitEntity.getId());
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
