package com.dunnas.chamados_condominio.application.usecases.block;

import com.dunnas.chamados_condominio.application.gateways.BlockGateway;
import com.dunnas.chamados_condominio.domain.entity.Block;

import java.util.List;

public class FindAllBlocks {
    private final BlockGateway blockGateway;

    public FindAllBlocks(BlockGateway blockGateway) {
        this.blockGateway = blockGateway;
    }

    public List<Block> findAllBlocks() {
        return blockGateway.findAllBlocks();
    }
}
