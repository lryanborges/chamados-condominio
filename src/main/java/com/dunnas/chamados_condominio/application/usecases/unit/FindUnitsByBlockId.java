package com.dunnas.chamados_condominio.application.usecases.unit;

import com.dunnas.chamados_condominio.application.exceptions.BadRequestException;
import com.dunnas.chamados_condominio.application.gateways.UnitGateway;
import com.dunnas.chamados_condominio.domain.entity.Unit;

import java.util.List;

public class FindUnitsByBlockId {
    private UnitGateway unitGateway;

    public FindUnitsByBlockId(UnitGateway unitGateway) {
        this.unitGateway = unitGateway;
    }

    public List<Unit> findUnitsByBlockId(Long blockId) {
        if (blockId == null) {
            throw new BadRequestException("Block ID cannot be null");
        }
        return unitGateway.findUnitByBlockId(blockId);
    }
}
