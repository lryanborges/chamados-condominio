package com.dunnas.chamados_condominio.application.usecases.block;

import com.dunnas.chamados_condominio.application.gateways.BlockGateway;
import com.dunnas.chamados_condominio.domain.entity.Block;

public class FindBlockById {
    private final BlockGateway blockGateway;

    public FindBlockById(BlockGateway blockGateway) {
        this.blockGateway = blockGateway;
    }

    Block findBlockById(Long id) {
        return blockGateway.findBlockById(id);
    }
}
