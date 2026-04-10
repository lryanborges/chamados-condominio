package com.dunnas.chamados_condominio.infrastructure.gateways;

import com.dunnas.chamados_condominio.application.gateways.BlockGateway;
import com.dunnas.chamados_condominio.domain.entity.Block;
import com.dunnas.chamados_condominio.infrastructure.persistence.BlockEntity;
import com.dunnas.chamados_condominio.infrastructure.persistence.BlockRepository;

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
}
