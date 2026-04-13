package com.dunnas.chamados_condominio.application.usecases.user;

import com.dunnas.chamados_condominio.application.gateways.UserGateway;

public class DeleteUser {
    private final UserGateway userGateway;

    public DeleteUser(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public void deleteUser(Long id) {
        userGateway.deleteUser(id);
    }
}
