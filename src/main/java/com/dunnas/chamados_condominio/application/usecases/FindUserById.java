package com.dunnas.chamados_condominio.application.usecases;

import com.dunnas.chamados_condominio.application.gateways.UserGateway;
import com.dunnas.chamados_condominio.domain.entity.User;

public class FindUserById {
    private final UserGateway userGateway;

    public FindUserById(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public User findUserById(Long id) {
        return userGateway.findUserById(id);
    }
}
