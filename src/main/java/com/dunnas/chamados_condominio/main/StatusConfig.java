package com.dunnas.chamados_condominio.main;

import com.dunnas.chamados_condominio.application.gateways.StatusGateway;
import com.dunnas.chamados_condominio.application.usecases.status.CreateStatus;
import com.dunnas.chamados_condominio.application.usecases.status.FindStatusById;
import com.dunnas.chamados_condominio.infrastructure.controllers.api.status.StatusDTOMapper;
import com.dunnas.chamados_condominio.infrastructure.gateways.status.StatusEntityMapper;
import com.dunnas.chamados_condominio.infrastructure.gateways.status.StatusRepositoryGateway;
import com.dunnas.chamados_condominio.infrastructure.persistence.status.StatusRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StatusConfig {

    @Bean
    CreateStatus createStatus(StatusGateway statusGateway) {
        return new CreateStatus(statusGateway);
    }

    @Bean
    FindStatusById findStatusById(StatusGateway statusGateway) {
        return new FindStatusById(statusGateway);
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
