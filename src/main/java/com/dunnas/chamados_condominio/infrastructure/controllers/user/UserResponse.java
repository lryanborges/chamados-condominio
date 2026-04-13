package com.dunnas.chamados_condominio.infrastructure.controllers.user;

import com.dunnas.chamados_condominio.domain.entity.Role;

public record UserResponse(String name, String email, Role role, String scope) {

}
