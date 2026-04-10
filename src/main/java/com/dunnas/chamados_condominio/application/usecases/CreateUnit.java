package com.dunnas.chamados_condominio.application.usecases;

import com.dunnas.chamados_condominio.application.gateways.UnitGateway;
import com.dunnas.chamados_condominio.domain.entity.Unit;

public class CreateUnit {
    private final UnitGateway unitGateway;

    public CreateUnit(UnitGateway unitGateway) {
        this.unitGateway = unitGateway;
    }

    public Unit createUnit(Unit newUnit) {
        return unitGateway.createUnit(newUnit);
    }
}
