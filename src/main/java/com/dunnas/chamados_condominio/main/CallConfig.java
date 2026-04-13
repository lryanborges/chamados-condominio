package com.dunnas.chamados_condominio.main;

import com.dunnas.chamados_condominio.application.gateways.AnnexGateway;
import com.dunnas.chamados_condominio.application.gateways.CallGateway;
import com.dunnas.chamados_condominio.application.usecases.call.CreateCall;
import com.dunnas.chamados_condominio.infrastructure.controllers.call.CallDTOMapper;
import com.dunnas.chamados_condominio.infrastructure.gateways.call.CallEntityMapper;
import com.dunnas.chamados_condominio.infrastructure.gateways.call.CallRepositoryGateway;
import com.dunnas.chamados_condominio.infrastructure.persistence.call.CallRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CallConfig {

    @Bean
    CreateCall createCall(CallGateway callGateway, AnnexGateway annexGateway) {
        return new CreateCall(callGateway, annexGateway);
    }

    @Bean
    CallGateway callGateway(CallRepository callRepository, CallEntityMapper callEntityMapper) {
        return new CallRepositoryGateway(callRepository, callEntityMapper);
    }

    @Bean
    CallEntityMapper callEntityMapper() {
        return new CallEntityMapper();
    }

    @Bean
    CallDTOMapper callDTOMapper() {
        return new CallDTOMapper();
    }
}
