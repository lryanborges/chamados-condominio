package com.dunnas.chamados_condominio.main;

import com.dunnas.chamados_condominio.application.gateways.UnitGateway;
import com.dunnas.chamados_condominio.application.gateways.UserGateway;
import com.dunnas.chamados_condominio.application.usecases.user.*;
import com.dunnas.chamados_condominio.infrastructure.controllers.api.user.UserDTOMapper;
import com.dunnas.chamados_condominio.infrastructure.gateways.user.UserEntityMapper;
import com.dunnas.chamados_condominio.infrastructure.gateways.user.UserRepositoryGateway;
import com.dunnas.chamados_condominio.infrastructure.persistence.unit.UnitRepository;
import com.dunnas.chamados_condominio.infrastructure.persistence.user.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserConfig {

    @Bean
    CreateUser createUser(UserGateway userGateway, PasswordEncoder passwordEncoder) {
        return new CreateUser(userGateway, passwordEncoder);
    }

    @Bean
    FindUserByEmail findUserByEmail(UserGateway userGateway) {
        return new FindUserByEmail(userGateway);
    }

    @Bean
    FindUserById findUserById(UserGateway userGateway) {
        return new FindUserById(userGateway);
    }

    @Bean
    FindAllUsers findAllUsers(UserGateway userGateway) {
        return new FindAllUsers(userGateway);
    }

    @Bean
    UpdateUser updateUser(UserGateway userGateway, PasswordEncoder passwordEncoder) {
        return new UpdateUser(userGateway, passwordEncoder);
    }

    @Bean
    DeleteUser deleteUser(UserGateway userGateway) {
        return new DeleteUser(userGateway);
    }

    @Bean
    LinkUserToUnit linkUserToUnit(UserGateway userGateway, UnitGateway unitGateway) {
        return new LinkUserToUnit(userGateway, unitGateway);
    }

    @Bean
    UserGateway userGateway(UserRepository userRepository, UserEntityMapper userEntityMapper, UnitRepository unitRepository) {
        return new UserRepositoryGateway(userRepository, userEntityMapper, unitRepository);
    }

    @Bean
    UserEntityMapper userEntityMapper() {
        return new UserEntityMapper();
    }

    @Bean
    UserDTOMapper userDTOMapper() {
        return new UserDTOMapper();
    }
}
