package com.dunnas.chamados_condominio.main;

import com.dunnas.chamados_condominio.application.gateways.StatusGateway;
import com.dunnas.chamados_condominio.application.usecases.CreateStatus;
import com.dunnas.chamados_condominio.infrastructure.controllers.StatusDTOMapper;
import com.dunnas.chamados_condominio.infrastructure.gateways.StatusEntityMapper;
import com.dunnas.chamados_condominio.infrastructure.gateways.StatusRepositoryGateway;
import com.dunnas.chamados_condominio.infrastructure.persistence.StatusEntity;
import com.dunnas.chamados_condominio.infrastructure.persistence.StatusRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StatusConfig {

    @Bean
    CreateStatus createStatus(StatusGateway statusGateway) {
        return new CreateStatus(statusGateway);
    }

    @Bean
    StatusGateway statusGateway(StatusRepository statusRepository, StatusEntityMapper statusEntityMapper) {
        return new StatusRepositoryGateway(statusRepository, statusEntityMapper);
    }

    @Bean
    StatusEntityMapper statusEntityMapper() {
        return new StatusEntityMapper();
    }

    @Bean
    StatusDTOMapper statusDTOMapper() {
        return new StatusDTOMapper();
    }

}
