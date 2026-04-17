package com.dunnas.chamados_condominio.main;

import com.dunnas.chamados_condominio.application.gateways.AnnexGateway;
import com.dunnas.chamados_condominio.application.gateways.CallGateway;
import com.dunnas.chamados_condominio.application.usecases.annex.FindAnnexById;
import com.dunnas.chamados_condominio.application.usecases.annex.FindAnnexesByCallId;
import com.dunnas.chamados_condominio.infrastructure.controllers.api.annex.AnnexDTOMapper;
import com.dunnas.chamados_condominio.infrastructure.controllers.api.call.CallDTOMapper;
import com.dunnas.chamados_condominio.infrastructure.gateways.annex.AnnexEntityMapper;
import com.dunnas.chamados_condominio.infrastructure.gateways.annex.AnnexRepositoryGateway;
import com.dunnas.chamados_condominio.infrastructure.gateways.call.CallEntityMapper;
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
    FindAnnexesByCallId findAnnexesByCallId(AnnexGateway annexGateway) {
        return new FindAnnexesByCallId(annexGateway);
    }

    @Bean
    FindAnnexById findAnnexById(AnnexGateway annexGateway) {
        return new FindAnnexById(annexGateway);
    }

    @Bean
    AnnexEntityMapper annexEntityMapper(CallEntityMapper callEntityMapper) {
        return new AnnexEntityMapper(callEntityMapper);
    }

    @Bean
    AnnexDTOMapper annexDTOMapper(CallDTOMapper callDTOMapper, CallGateway callGateway) {
        return new AnnexDTOMapper(callDTOMapper, callGateway);
    }
}
