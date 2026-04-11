package com.dunnas.chamados_condominio.main;

import com.dunnas.chamados_condominio.application.gateways.CallTypeGateway;
import com.dunnas.chamados_condominio.application.usecases.CreateCallType;
import com.dunnas.chamados_condominio.infrastructure.controllers.CallTypeDTOMapper;
import com.dunnas.chamados_condominio.infrastructure.gateways.CallTypeEntityMapper;
import com.dunnas.chamados_condominio.infrastructure.gateways.CallTypeRepositoryGateway;
import com.dunnas.chamados_condominio.infrastructure.persistence.CallTypeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CallTypeConfig {
    @Bean
    CreateCallType createCallType(CallTypeGateway callTypeGateway) {
        return new CreateCallType(callTypeGateway);
    }

    @Bean
    CallTypeGateway callTypeGateway(CallTypeRepository callTypeRepository, CallTypeEntityMapper callTypeEntityMapper) {
        return new CallTypeRepositoryGateway(callTypeRepository, callTypeEntityMapper);
    }

    @Bean
    CallTypeEntityMapper callTypeEntityMapper() {
        return new CallTypeEntityMapper();
    }

    @Bean
    CallTypeDTOMapper callTypeDTOMapper() {
        return new CallTypeDTOMapper();
    }
}
