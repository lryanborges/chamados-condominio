package com.dunnas.chamados_condominio.main;

import com.dunnas.chamados_condominio.application.gateways.UnitGateway;
import com.dunnas.chamados_condominio.application.usecases.CreateUnit;
import com.dunnas.chamados_condominio.infrastructure.gateways.UnitEntityMapper;
import com.dunnas.chamados_condominio.infrastructure.gateways.UnitRepositoryGateway;
import com.dunnas.chamados_condominio.infrastructure.persistence.UnitRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UnitConfig {

    @Bean
    CreateUnit createUnit(UnitGateway unitGateway) {
        return new CreateUnit(unitGateway);
    }

    @Bean
    UnitGateway unitGateway(UnitRepository repository, UnitEntityMapper mapper) {
        return new UnitRepositoryGateway(repository, mapper);
    }

    @Bean
    UnitEntityMapper unitEntityMapper() {
        return new UnitEntityMapper();
    }

}
