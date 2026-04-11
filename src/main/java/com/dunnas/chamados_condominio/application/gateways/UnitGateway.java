package com.dunnas.chamados_condominio.application.gateways;

import com.dunnas.chamados_condominio.domain.entity.Unit;

public interface UnitGateway {
    Unit createUnit(Unit newUnit);
    Unit findUnitById(Long id);
}
