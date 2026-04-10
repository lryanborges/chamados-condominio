package com.dunnas.chamados_condominio.infrastructure.gateways;

import com.dunnas.chamados_condominio.domain.entity.User;
import com.dunnas.chamados_condominio.infrastructure.persistence.UserEntity;

public class UserEntityMapper {
    UserEntity toEntity(User userDomainObj) {
        return new UserEntity(userDomainObj.getName(), userDomainObj.getEmail(), userDomainObj.getPassword(), userDomainObj.getRole(), userDomainObj.getScope());
    }

    User toDomainObj(UserEntity userEntity) {
        return new User(userEntity.getName(), userEntity.getEmail(), userEntity.getPassword(), userEntity.getRole(), userEntity.getScope());
    }
}
