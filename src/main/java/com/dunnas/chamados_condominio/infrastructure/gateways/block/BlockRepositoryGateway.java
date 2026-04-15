package com.dunnas.chamados_condominio.infrastructure.gateways.block;

import com.dunnas.chamados_condominio.application.exceptions.NotFoundException;
import com.dunnas.chamados_condominio.application.gateways.BlockGateway;
import com.dunnas.chamados_condominio.domain.entity.Block;
import com.dunnas.chamados_condominio.infrastructure.persistence.block.BlockEntity;
import com.dunnas.chamados_condominio.infrastructure.persistence.block.BlockRepository;

import java.util.List;

public class BlockRepositoryGateway implements BlockGateway {

    private final BlockRepository repository;
    private final BlockEntityMapper mapper;

    public BlockRepositoryGateway(BlockRepository blockRepository, BlockEntityMapper blockEntityMapper) {
        this.repository = blockRepository;
        this.mapper = blockEntityMapper;
    }

    @Override
    public Block createBlock(Block newBlock) {
        BlockEntity blockEntity = mapper.toEntity(newBlock);
        BlockEntity savedBlock = repository.save(blockEntity);
        return mapper.toDomainObj(savedBlock);
    }

    @Override
    public List<Block> findAllBlocks() {
        List<BlockEntity> blockEntities = repository.findAll();
        return mapper.toDomainObjList(blockEntities);
    }

    @Override
    public Block findBlockById(Long id) {
        BlockEntity foundBlock = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Block not found"));
        return mapper.toDomainObj(foundBlock);
    }
}
