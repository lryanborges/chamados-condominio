package com.dunnas.chamados_condominio.application.gateways;

import com.dunnas.chamados_condominio.domain.entity.Unit;

import java.util.List;

public interface UnitGateway {
    Unit createUnit(Unit newUnit);
    Unit findUnitById(Long id);
    List<Unit> findUnitByBlockId(Long blockId);
}
