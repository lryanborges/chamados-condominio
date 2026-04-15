package com.dunnas.chamados_condominio.main;

import com.dunnas.chamados_condominio.application.gateways.UnitGateway;
import com.dunnas.chamados_condominio.application.usecases.unit.CreateUnit;
import com.dunnas.chamados_condominio.application.usecases.unit.FindUnitById;
import com.dunnas.chamados_condominio.application.usecases.unit.FindUnitsByBlockId;
import com.dunnas.chamados_condominio.infrastructure.gateways.unit.UnitEntityMapper;
import com.dunnas.chamados_condominio.infrastructure.gateways.unit.UnitRepositoryGateway;
import com.dunnas.chamados_condominio.infrastructure.persistence.unit.UnitRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UnitConfig {

    @Bean
    CreateUnit createUnit(UnitGateway unitGateway) {
        return new CreateUnit(unitGateway);
    }

    @Bean
    FindUnitById findUnitById(UnitGateway unitGateway) {
        return new FindUnitById(unitGateway);
    }

    @Bean
    FindUnitsByBlockId findUnitsByBlockId(UnitGateway unitGateway) {
        return new FindUnitsByBlockId(unitGateway);
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
