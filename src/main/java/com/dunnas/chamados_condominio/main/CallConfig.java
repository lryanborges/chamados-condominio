package com.dunnas.chamados_condominio.main;

import com.dunnas.chamados_condominio.application.gateways.*;
import com.dunnas.chamados_condominio.application.usecases.call.CreateCall;
import com.dunnas.chamados_condominio.application.usecases.call.FindAllCallByFilters;
import com.dunnas.chamados_condominio.application.usecases.call.FindCallById;
import com.dunnas.chamados_condominio.application.usecases.call.UpdateCall;
import com.dunnas.chamados_condominio.infrastructure.controllers.call.CallDTOMapper;
import com.dunnas.chamados_condominio.infrastructure.gateways.call.CallEntityMapper;
import com.dunnas.chamados_condominio.infrastructure.gateways.call.CallRepositoryGateway;
import com.dunnas.chamados_condominio.infrastructure.persistence.call.CallRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CallConfig {

    @Bean
    CreateCall createCall(CallGateway callGateway, AnnexGateway annexGateway, FileStorageGateway fileStorageGateway, UserGateway userGateway) {
        return new CreateCall(callGateway, annexGateway, fileStorageGateway, userGateway);
    }

    @Bean
    FindAllCallByFilters findAllCallByFilters(CallGateway callGateway, UserGateway userGateway) {
        return new FindAllCallByFilters(callGateway, userGateway);
    }

    @Bean
    FindCallById findCallById(CallGateway callGateway) {
        return new FindCallById(callGateway);
    }

    @Bean
    UpdateCall updateCall(CallGateway callGateway, StatusGateway statusGateway, UserGateway userGateway, CallTypeGateway callTypeGateway) {
        return new UpdateCall(callGateway, statusGateway, userGateway, callTypeGateway);
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
