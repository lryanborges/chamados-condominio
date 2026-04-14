package com.dunnas.chamados_condominio.main;

import com.dunnas.chamados_condominio.application.gateways.AnnexGateway;
import com.dunnas.chamados_condominio.infrastructure.controllers.api.annex.AnnexDTOMapper;
import com.dunnas.chamados_condominio.infrastructure.gateways.annex.AnnexEntityMapper;
import com.dunnas.chamados_condominio.infrastructure.gateways.annex.AnnexRepositoryGateway;
import com.dunnas.chamados_condominio.infrastructure.persistence.annex.AnnexRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnnexConfig {
    @Bean
    AnnexGateway annexGateway(AnnexRepository annexRepository, AnnexEntityMapper annexEntityMapper) {
        return new AnnexRepositoryGateway(annexRepository, annexEntityMapper);
    }

    @Bean
    AnnexEntityMapper annexEntityMapper() {
        return new AnnexEntityMapper();
    }

    @Bean
    AnnexDTOMapper annexDTOMapper() {
        return new AnnexDTOMapper();
    }
}
