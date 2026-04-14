package com.dunnas.chamados_condominio.application.usecases.user;

import com.dunnas.chamados_condominio.application.exceptions.BadRequestException;
import com.dunnas.chamados_condominio.application.exceptions.NotFoundException;
import com.dunnas.chamados_condominio.application.gateways.UserGateway;
import com.dunnas.chamados_condominio.domain.entity.User;

public class FindUserById {
    private final UserGateway userGateway;

    public FindUserById(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public User findUserById(Long id) {
        if (id == null) {
            throw new BadRequestException("User id must not be null");
        }

        User user = userGateway.findUserById(id);

        if (user == null) {
            throw new NotFoundException("User not found");
        }

        return user;
    }
}
