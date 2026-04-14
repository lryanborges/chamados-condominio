package com.dunnas.chamados_condominio.main;

import com.dunnas.chamados_condominio.application.gateways.BlockGateway;
import com.dunnas.chamados_condominio.application.usecases.block.CreateBlock;
import com.dunnas.chamados_condominio.application.usecases.unit.CreateUnit;
import com.dunnas.chamados_condominio.infrastructure.controllers.api.block.BlockDTOMapper;
import com.dunnas.chamados_condominio.infrastructure.gateways.block.BlockEntityMapper;
import com.dunnas.chamados_condominio.infrastructure.gateways.block.BlockRepositoryGateway;
import com.dunnas.chamados_condominio.infrastructure.persistence.block.BlockRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BlockConfig {

    @Bean
    CreateBlock createBlock(BlockGateway blockGateway, CreateUnit createUnit) {
        return new CreateBlock(blockGateway, createUnit);
    }

    @Bean
    BlockGateway blockGateway(BlockRepository blockRepository, BlockEntityMapper blockEntityMapper) {
        return new BlockRepositoryGateway(blockRepository, blockEntityMapper);
    }

    @Bean
    BlockEntityMapper blockEntityMapper() {
        return new BlockEntityMapper();
    }

    @Bean
    BlockDTOMapper blockDTOMapper() {
        return new BlockDTOMapper();
    }
}
