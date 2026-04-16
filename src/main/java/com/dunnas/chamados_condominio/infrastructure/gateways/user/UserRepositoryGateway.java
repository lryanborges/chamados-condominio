package com.dunnas.chamados_condominio.infrastructure.gateways.user;

import com.dunnas.chamados_condominio.application.gateways.UserGateway;
import com.dunnas.chamados_condominio.domain.entity.Unit;
import com.dunnas.chamados_condominio.domain.entity.User;
import com.dunnas.chamados_condominio.infrastructure.persistence.unit.UnitEntity;
import com.dunnas.chamados_condominio.infrastructure.persistence.unit.UnitRepository;
import com.dunnas.chamados_condominio.infrastructure.persistence.user.UserEntity;
import com.dunnas.chamados_condominio.infrastructure.persistence.user.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

public class UserRepositoryGateway implements UserGateway {

    private final UserRepository repository;
    private final UserEntityMapper mapper;
    private final UnitRepository unitRepository;

    public UserRepositoryGateway(UserRepository repository, UserEntityMapper mapper, UnitRepository unitRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.unitRepository = unitRepository;
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
        List<UserEntity> foundUsers = repository.findAllByOrderByIdAsc();
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

    @Override
    public void linkUserToUnit(Long userId, Unit unit) {
        UserEntity userEntity = repository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        UnitEntity unitEntity = unitRepository.findById(unit.getId())
                .orElseThrow(() -> new RuntimeException("Unit not found"));

        userEntity.getUnits().add(unitEntity);
        repository.save(userEntity);
    }
}
