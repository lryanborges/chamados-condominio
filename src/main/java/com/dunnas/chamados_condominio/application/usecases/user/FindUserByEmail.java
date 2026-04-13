package com.dunnas.chamados_condominio.application.usecases.user;

import com.dunnas.chamados_condominio.application.gateways.UserGateway;
import com.dunnas.chamados_condominio.domain.entity.User;

public class FindUserByEmail {
    private final UserGateway userGateway;

    public FindUserByEmail(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public User findUserByEmail(String email) {
        return userGateway.findUserByEmail(email);
    }
}
