package com.dunnas.chamados_condominio.infrastructure.controllers;

import com.dunnas.chamados_condominio.domain.entity.Role;

public record CreateUserResponse(String name, String email, Role role, String Scope) {

}
