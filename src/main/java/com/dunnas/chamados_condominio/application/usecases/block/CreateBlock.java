package com.dunnas.chamados_condominio.application.usecases.block;

import com.dunnas.chamados_condominio.application.gateways.BlockGateway;
import com.dunnas.chamados_condominio.application.gateways.UnitGateway;
import com.dunnas.chamados_condominio.application.usecases.unit.CreateUnit;
import com.dunnas.chamados_condominio.domain.entity.Block;
import com.dunnas.chamados_condominio.domain.entity.Unit;

public class CreateBlock {

    private final BlockGateway blockGateway;
    private final UnitGateway unitGateway;

    public CreateBlock(BlockGateway blockGateway, UnitGateway unitGateway) {
        this.blockGateway = blockGateway;
        this.unitGateway = unitGateway;
    }

    public Block createBlock(Block newBlock){
        Block createdBlock = blockGateway.createBlock(newBlock);
        int qtdFloors = createdBlock.getQtdFloors();
        int nUnitsPerFloor =  createdBlock.getnUnitsPerFloor();

        for(int i = 0; i < qtdFloors; i++){
            for(int j = 1; j <= nUnitsPerFloor; j++){
                String identifier = i + String.format("%02d", j);
                unitGateway.createUnit(new Unit(createdBlock, i, identifier));
            }
        }
        return createdBlock;
    }
}
