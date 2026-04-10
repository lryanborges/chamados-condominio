package com.dunnas.chamados_condominio.main;

import com.dunnas.chamados_condominio.application.gateways.UserGateway;
import com.dunnas.chamados_condominio.application.usecases.CreateUser;
import com.dunnas.chamados_condominio.application.usecases.FindAllUsers;
import com.dunnas.chamados_condominio.application.usecases.FindUserByEmail;
import com.dunnas.chamados_condominio.application.usecases.FindUserById;
import com.dunnas.chamados_condominio.infrastructure.controllers.UserDTOMapper;
import com.dunnas.chamados_condominio.infrastructure.gateways.UserEntityMapper;
import com.dunnas.chamados_condominio.infrastructure.gateways.UserRepositoryGateway;
import com.dunnas.chamados_condominio.infrastructure.persistence.UserRepository;
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
    UserGateway userGateway(UserRepository userRepository, UserEntityMapper userEntityMapper) {
        return new UserRepositoryGateway(userRepository, userEntityMapper);
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
