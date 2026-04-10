package com.dunnas.chamados_condominio.application.gateways;

import com.dunnas.chamados_condominio.domain.entity.User;

import java.util.List;

public interface UserGateway {
    User createUser(User user);
}
