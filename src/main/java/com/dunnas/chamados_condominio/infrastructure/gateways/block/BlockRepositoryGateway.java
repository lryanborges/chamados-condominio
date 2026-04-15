package com.dunnas.chamados_condominio.infrastructure.gateways.block;

import com.dunnas.chamados_condominio.application.gateways.BlockGateway;
import com.dunnas.chamados_condominio.domain.entity.Block;
import com.dunnas.chamados_condominio.infrastructure.persistence.block.BlockEntity;
import com.dunnas.chamados_condominio.infrastructure.persistence.block.BlockRepository;

import java.util.List;

public class BlockRepositoryGateway implements BlockGateway {

    private final BlockRepository blockRepository;
    private final BlockEntityMapper blockEntityMapper;

    public BlockRepositoryGateway(BlockRepository blockRepository, BlockEntityMapper blockEntityMapper) {
        this.blockRepository = blockRepository;
        this.blockEntityMapper = blockEntityMapper;
    }

    @Override
    public Block createBlock(Block newBlock) {
        BlockEntity blockEntity = blockEntityMapper.toEntity(newBlock);
        BlockEntity savedBlock = blockRepository.save(blockEntity);
        return blockEntityMapper.toDomainObj(savedBlock);
    }

    @Override
    public List<Block> findAllBlocks() {
        List<BlockEntity> blockEntities = blockRepository.findAll();
        return blockEntityMapper.toDomainObjList(blockEntities);
    }
}
