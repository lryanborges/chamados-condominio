package com.dunnas.chamados_condominio.infrastructure.gateways;

import com.dunnas.chamados_condominio.application.gateways.UserGateway;
import com.dunnas.chamados_condominio.domain.entity.User;
import com.dunnas.chamados_condominio.infrastructure.persistence.UserEntity;
import com.dunnas.chamados_condominio.infrastructure.persistence.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

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

    @Override
    public User findUserByEmail(String email) {
        UserEntity foundUser = repository.findByEmail(email);
        if (foundUser == null) {
            throw new RuntimeException("User not found");
        }
        return mapper.toDomainObj(foundUser);
    }

    @Override
    public User findUserById(Long id) {
        UserEntity foundUser = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return mapper.toDomainObj(foundUser);
    }

    @Override
    public List<User> findAllUsers() {
        List<UserEntity> foundUsers = repository.findAll();
        return mapper.toDomainObjList(foundUsers);
    }

    @Override
    public User updateUser(User user) {
        UserEntity userEntity = mapper.toEntity(user);
        UserEntity savedUser = repository.save(userEntity);
        return mapper.toDomainObj(savedUser);
    }

    @Override
    public void deleteUser(Long id) {
        UserEntity user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setDeletedAt(LocalDateTime.now());
        repository.save(user);
    }
}
