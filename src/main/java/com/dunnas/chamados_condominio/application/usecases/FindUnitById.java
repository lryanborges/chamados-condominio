package com.dunnas.chamados_condominio.application.usecases;

import com.dunnas.chamados_condominio.application.gateways.UnitGateway;
import com.dunnas.chamados_condominio.domain.entity.Unit;

public class FindUnitById {
    private final UnitGateway unitGateway;

    public FindUnitById(UnitGateway unitGateway) {
        this.unitGateway = unitGateway;
    }

    public Unit findUnitById(Long id) {
        return unitGateway.findUnitById(id);
    }
}
