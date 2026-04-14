package com.dunnas.chamados_condominio.application.usecases.unit;

import com.dunnas.chamados_condominio.application.exceptions.BadRequestException;
import com.dunnas.chamados_condominio.application.exceptions.NotFoundException;
import com.dunnas.chamados_condominio.application.gateways.UnitGateway;
import com.dunnas.chamados_condominio.domain.entity.Unit;

public class FindUnitById {
    private final UnitGateway unitGateway;

    public FindUnitById(UnitGateway unitGateway) {
        this.unitGateway = unitGateway;
    }

    public Unit findUnitById(Long id) {
        if (id == null) {
            throw new BadRequestException("Unit id must not be null");
        }

        Unit unit = unitGateway.findUnitById(id);

        if (unit == null) {
            throw new NotFoundException("Unit not found");
        }

        return unit;
    }
}
