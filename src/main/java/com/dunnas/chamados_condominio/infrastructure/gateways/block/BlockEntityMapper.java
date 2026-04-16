package com.dunnas.chamados_condominio.infrastructure.gateways.block;

import com.dunnas.chamados_condominio.domain.entity.Block;
import com.dunnas.chamados_condominio.infrastructure.persistence.block.BlockEntity;

import java.util.ArrayList;
import java.util.List;

public class BlockEntityMapper {
    public BlockEntity toEntity(Block blockDomainObj) {
        BlockEntity blockEntity = new BlockEntity(blockDomainObj.getQtdFloors(), blockDomainObj.getnUnitsPerFloor(), blockDomainObj.getIdentity());
        blockEntity.setId(blockDomainObj.getId());
        return blockEntity;
    }

    public Block toDomainObj(BlockEntity blockEntity) {
        Block block = new Block(blockEntity.getQtdFloors(), blockEntity.getnUnitsPerFloor(), blockEntity.getIdentity());
        block.setId(blockEntity.getId());
        return block;
    }

    public List<Block> toDomainObjList(List<BlockEntity> blockEntitiesList) {
        return blockEntitiesList.stream()
                .map(this::toDomainObj)
                .toList();
    }

    public List<BlockEntity> toEntityList(List<Block> blockDomainObjList) {
        return blockDomainObjList.stream()
                .map(this::toEntity)
                .toList();
    }
}
