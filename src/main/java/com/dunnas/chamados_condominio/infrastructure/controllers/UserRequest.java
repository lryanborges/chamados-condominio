package com.dunnas.chamados_condominio.infrastructure.controllers;

import com.dunnas.chamados_condominio.domain.entity.Role;

public record UserRequest(String name, String email, String password, Role role, String scope) {

}
