package com.dunnas.chamados_condominio.infrastructure.controllers.api.user;

import com.dunnas.chamados_condominio.domain.entity.Role;

public record UserRequest(String name, String email, String password, String confirmPassword, Role role, String scope) {

}
