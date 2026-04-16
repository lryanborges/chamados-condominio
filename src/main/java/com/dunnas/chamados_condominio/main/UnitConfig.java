package com.dunnas.chamados_condominio.main;

import com.dunnas.chamados_condominio.application.gateways.UnitGateway;
import com.dunnas.chamados_condominio.application.usecases.unit.CreateUnit;
import com.dunnas.chamados_condominio.application.usecases.unit.FindUnitById;
import com.dunnas.chamados_condominio.application.usecases.unit.FindUnitsByBlockId;
import com.dunnas.chamados_condominio.application.usecases.unit.FindUnitsByUserId;
import com.dunnas.chamados_condominio.infrastructure.gateways.block.BlockEntityMapper;
import com.dunnas.chamados_condominio.infrastructure.gateways.unit.UnitEntityMapper;
import com.dunnas.chamados_condominio.infrastructure.gateways.unit.UnitRepositoryGateway;
import com.dunnas.chamados_condominio.infrastructure.gateways.user.UserEntityMapper;
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
    FindUnitsByUserId findUnitsByUserId(UnitGateway unitGateway) {
        return new FindUnitsByUserId(unitGateway);
    }

    @Bean
    UnitGateway unitGateway(UnitRepository repository, UnitEntityMapper mapper, BlockEntityMapper blockEntityMapper) {
        return new UnitRepositoryGateway(repository, mapper, blockEntityMapper);
    }

    @Bean
    UnitEntityMapper unitEntityMapper(UserEntityMapper userEntityMapper, BlockEntityMapper blockEntityMapper) {
        return new UnitEntityMapper(userEntityMapper, blockEntityMapper);
    }

}
