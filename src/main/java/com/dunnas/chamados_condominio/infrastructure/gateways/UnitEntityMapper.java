package com.dunnas.chamados_condominio.infrastructure.gateways;

import com.dunnas.chamados_condominio.domain.entity.Unit;
import com.dunnas.chamados_condominio.infrastructure.persistence.UnitEntity;

public class UnitEntityMapper {
    UnitEntity toEntity(Unit unitDomainObj) {
        return new UnitEntity(unitDomainObj.getBlockId(), unitDomainObj.getFloor(), unitDomainObj.getIdentifier());
    }

    Unit toDomainObj(UnitEntity unitEntity) {
        return new Unit(unitEntity.getBlockId(), unitEntity.getFloor(), unitEntity.getIdentifier());
    }
}
