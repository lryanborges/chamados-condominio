package com.dunnas.chamados_condominio.infrastructure.gateways.block;

import com.dunnas.chamados_condominio.domain.entity.Block;
import com.dunnas.chamados_condominio.infrastructure.persistence.block.BlockEntity;

public class BlockEntityMapper {
    BlockEntity toEntity(Block blockDomainObj) {
        return new BlockEntity(blockDomainObj.getQtdFloors(), blockDomainObj.getnUnitsPerFloor(), blockDomainObj.getIdentity());
    }

    Block toDomainObj(BlockEntity blockEntity) {
        Block block = new Block(blockEntity.getQtdFloors(), blockEntity.getnUnitsPerFloor(), blockEntity.getIdentity());
        block.setId(blockEntity.getId());
        return block;
    }
}
