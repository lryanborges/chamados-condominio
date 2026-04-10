package com.dunnas.chamados_condominio.infrastructure.controllers;

import com.dunnas.chamados_condominio.domain.entity.User;

public class UserDTOMapper {
    CreateUserResponse toResponse(User user) {
        return new CreateUserResponse(user.getName(), user.getEmail(), user.getRole(), user.getScope());
    }

    User toEntity(CreateUserRequest request) {
        return new User(request.name(), request.email(), request.password(), request.role(), request.scope());
    }
}
