package com.dunnas.chamados_condominio.infrastructure.gateways;

import com.dunnas.chamados_condominio.domain.entity.User;
import com.dunnas.chamados_condominio.infrastructure.persistence.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class UserEntityMapper {
    UserEntity toEntity(User userDomainObj) {
        UserEntity entity = new UserEntity(userDomainObj.getName(), userDomainObj.getEmail(), userDomainObj.getPassword(), userDomainObj.getRole(), userDomainObj.getScope());
        entity.setId(userDomainObj.getId());
        return entity;
    }

    User toDomainObj(UserEntity userEntity) {
        User user = new User(userEntity.getName(), userEntity.getEmail(), userEntity.getPassword(), userEntity.getRole(), userEntity.getScope());
        user.setId(userEntity.getId());
        return user;
    }

    List<User> toDomainObjList(List<UserEntity> userEntityList) {
        return userEntityList.stream()
                .map(this::toDomainObj)
                .toList();
    }

    List<UserEntity> toEntityList(List<User> userList) {
        return userList.stream()
                .map(this::toEntity)
                .toList();
    }
}
