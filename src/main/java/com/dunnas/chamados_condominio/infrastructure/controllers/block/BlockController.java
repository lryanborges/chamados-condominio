package com.dunnas.chamados_condominio.infrastructure.controllers.block;

import com.dunnas.chamados_condominio.application.usecases.block.CreateBlock;
import com.dunnas.chamados_condominio.domain.entity.Block;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("blocks")
public class BlockController {
    private final CreateBlock createBlock;
    private final BlockDTOMapper blockDTOMapper;

    public BlockController(CreateBlock createBlock, BlockDTOMapper blockDTOMapper) {
        this.createBlock = createBlock;
        this.blockDTOMapper = blockDTOMapper;
    }

    @PostMapping
    CreateBlockResponse createBlock(@RequestBody CreateBlockRequest request) {
        Block newBlock = blockDTOMapper.toEntity(request);
        Block createdBlock = createBlock.createBlock(newBlock);
        return blockDTOMapper.toResponse(createdBlock);
    }
}
