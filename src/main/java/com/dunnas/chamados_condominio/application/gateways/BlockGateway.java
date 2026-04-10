package com.dunnas.chamados_condominio.application.gateways;

import com.dunnas.chamados_condominio.domain.entity.Block;

public interface BlockGateway {
    Block createBlock(Block newBlock);
}
