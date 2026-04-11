package com.dunnas.chamados_condominio.infrastructure.gateways;

import com.dunnas.chamados_condominio.domain.entity.Unit;
import com.dunnas.chamados_condominio.infrastructure.persistence.UnitEntity;

public class UnitEntityMapper {
    UnitEntity toEntity(Unit unitDomainObj) {
        UnitEntity unitEntity = new UnitEntity(unitDomainObj.getBlockId(), unitDomainObj.getFloor(), unitDomainObj.getIdentifier());
        unitEntity.setId(unitDomainObj.getId());
        return unitEntity;
    }

    Unit toDomainObj(UnitEntity unitEntity) {
        Unit unit = new Unit(unitEntity.getBlockId(), unitEntity.getFloor(), unitEntity.getIdentifier());
        unit.setId(unitEntity.getId());
        return unit;
    }
}
