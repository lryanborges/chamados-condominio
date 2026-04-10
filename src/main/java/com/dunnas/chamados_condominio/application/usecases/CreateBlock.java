package com.dunnas.chamados_condominio.application.usecases;

import com.dunnas.chamados_condominio.application.gateways.BlockGateway;
import com.dunnas.chamados_condominio.domain.entity.Block;
import com.dunnas.chamados_condominio.domain.entity.Unit;

public class CreateBlock {

    private final BlockGateway blockGateway;
    private final CreateUnit createUnit;

    public CreateBlock(BlockGateway blockGateway, CreateUnit createUnit) {
        this.blockGateway = blockGateway;
        this.createUnit = createUnit;
    }

    public Block createBlock(Block newBlock){
        Block createdBlock = blockGateway.createBlock(newBlock);
        int qtdFloors = createdBlock.getQtdFloors();
        int nUnitsPerFloor =  createdBlock.getnUnitsPerFloor();

        for(int i = 0; i < qtdFloors; i++){
            for(int j = 1; j <= nUnitsPerFloor; j++){
                String identifier = i + String.format("%02d", j);
                createUnit.createUnit(new Unit(createdBlock.getId(), i, identifier));
            }
        }
        return createdBlock;
    }
}
