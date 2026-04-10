package com.dunnas.chamados_condominio.application.gateways;

import com.dunnas.chamados_condominio.domain.entity.User;

public interface UserGateway {
    User createUser(User user);
    User findUserByEmail(String email);
}
