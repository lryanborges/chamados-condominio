package com.dunnas.chamados_condominio.infrastructure.gateways.block;

import com.dunnas.chamados_condominio.domain.entity.Block;
import com.dunnas.chamados_condominio.infrastructure.persistence.block.BlockEntity;

import java.util.ArrayList;
import java.util.List;

public class BlockEntityMapper {
    BlockEntity toEntity(Block blockDomainObj) {
        return new BlockEntity(blockDomainObj.getQtdFloors(), blockDomainObj.getnUnitsPerFloor(), blockDomainObj.getIdentity());
    }

    Block toDomainObj(BlockEntity blockEntity) {
        Block block = new Block(blockEntity.getQtdFloors(), blockEntity.getnUnitsPerFloor(), blockEntity.getIdentity());
        block.setId(blockEntity.getId());
        return block;
    }

    List<Block> toDomainObjList(List<BlockEntity> blockEntitiesList) {
        return blockEntitiesList.stream()
                .map(this::toDomainObj)
                .toList();
    }

    List<BlockEntity> toEntityList(List<Block> blockDomainObjList) {
        return blockDomainObjList.stream()
                .map(this::toEntity)
                .toList();
    }
}
