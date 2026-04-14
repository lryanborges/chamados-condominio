package com.dunnas.chamados_condominio.infrastructure.controllers.api.block;

import com.dunnas.chamados_condominio.domain.entity.Block;

public class BlockDTOMapper {
    CreateBlockResponse toResponse(Block block) {
        int nUnities = block.getQtdFloors() * block.getnUnitsPerFloor();
        return new CreateBlockResponse(block.getId(), block.getQtdFloors(), block.getnUnitsPerFloor(), nUnities, block.getIdentity());
    }

    Block toEntity(CreateBlockRequest request) {
        return new Block(request.qtdFloors(), request.nUnitsPerFloor(), request.identity());
    }
}
