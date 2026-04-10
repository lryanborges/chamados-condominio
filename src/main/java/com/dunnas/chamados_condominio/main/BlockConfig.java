package com.dunnas.chamados_condominio.main;

import com.dunnas.chamados_condominio.application.gateways.BlockGateway;
import com.dunnas.chamados_condominio.application.usecases.CreateBlock;
import com.dunnas.chamados_condominio.infrastructure.controllers.BlockDTOMapper;
import com.dunnas.chamados_condominio.infrastructure.gateways.BlockEntityMapper;
import com.dunnas.chamados_condominio.infrastructure.gateways.BlockRepositoryGateway;
import com.dunnas.chamados_condominio.infrastructure.persistence.BlockRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BlockConfig {

    @Bean
    CreateBlock createBlock(BlockGateway blockGateway) {
        return new CreateBlock(blockGateway);
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
