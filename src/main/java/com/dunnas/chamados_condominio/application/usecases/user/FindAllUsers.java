package com.dunnas.chamados_condominio.application.usecases.user;

import com.dunnas.chamados_condominio.application.gateways.UserGateway;
import com.dunnas.chamados_condominio.domain.entity.User;

import java.util.List;

public class FindAllUsers {
    private final UserGateway userGateway;

    public FindAllUsers(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public List<User> findAllUsers(){
        return userGateway.findAllUsers();
    }
}
