package com.dunnas.chamados_condominio.application.gateways;

import com.dunnas.chamados_condominio.domain.entity.Block;

import java.util.List;

public interface BlockGateway {
    Block createBlock(Block newBlock);
    List<Block> findAllBlocks();
}
