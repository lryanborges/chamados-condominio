package com.dunnas.chamados_condominio.infrastructure.controllers.api.user;

import com.dunnas.chamados_condominio.domain.entity.User;

public class UserDTOMapper {
    public UserResponse toResponse(User user) {
        return new UserResponse(user.getName(), user.getEmail(), user.getRole(), user.getScope());
    }

    public User toEntity(UserRequest request) {
        return new User(request.name(), request.email(), request.password(), request.role(), request.scope());
    }
}
