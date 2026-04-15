package com.dunnas.chamados_condominio.application.usecases.unit;

import com.dunnas.chamados_condominio.application.gateways.UnitGateway;
import com.dunnas.chamados_condominio.domain.entity.Unit;

import java.util.List;

public class FindUnitsByUserId {
    private final UnitGateway unitGateway;

    public FindUnitsByUserId(UnitGateway unitGateway) {
        this.unitGateway = unitGateway;
    }

    public List<Unit> findUnitsByUserId(Long userId) {
        return unitGateway.findUnitByUserId(userId);
    }
}
