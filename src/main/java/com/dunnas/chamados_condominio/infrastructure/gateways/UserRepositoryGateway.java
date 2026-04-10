package com.dunnas.chamados_condominio.infrastructure.gateways;

import com.dunnas.chamados_condominio.application.gateways.UserGateway;
import com.dunnas.chamados_condominio.domain.entity.User;
import com.dunnas.chamados_condominio.infrastructure.persistence.UserEntity;
import com.dunnas.chamados_condominio.infrastructure.persistence.UserRepository;

public class UserRepositoryGateway implements UserGateway {

    private final UserRepository repository;
    private final UserEntityMapper mapper;

    public UserRepositoryGateway(UserRepository repository, UserEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public User createUser(User user) {
        UserEntity userEntity = mapper.toEntity(user);
        UserEntity savedUser = repository.save(userEntity);
        return mapper.toDomainObj(savedUser);
    }
}
