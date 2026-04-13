package com.dunnas.chamados_condominio.main;

import com.dunnas.chamados_condominio.application.gateways.CallTypeGateway;
import com.dunnas.chamados_condominio.application.usecases.calltype.CreateCallType;
import com.dunnas.chamados_condominio.infrastructure.controllers.calltype.CallTypeDTOMapper;
import com.dunnas.chamados_condominio.infrastructure.gateways.calltype.CallTypeEntityMapper;
import com.dunnas.chamados_condominio.infrastructure.gateways.calltype.CallTypeRepositoryGateway;
import com.dunnas.chamados_condominio.infrastructure.persistence.calltype.CallTypeRepository;
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
