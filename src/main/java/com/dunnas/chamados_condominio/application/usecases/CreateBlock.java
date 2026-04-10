package com.dunnas.chamados_condominio.application.usecases;

import com.dunnas.chamados_condominio.application.gateways.BlockGateway;
import com.dunnas.chamados_condominio.domain.entity.Block;

public class CreateBlock {

    private BlockGateway blockGateway;

    public CreateBlock(BlockGateway blockGateway) {
        this.blockGateway = blockGateway;
    }

    public Block createBlock(Block newBlock){
        return blockGateway.createBlock(newBlock);
    }
}
