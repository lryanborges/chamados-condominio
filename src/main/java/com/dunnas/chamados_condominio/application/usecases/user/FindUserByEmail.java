package com.dunnas.chamados_condominio.application.usecases.user;

import com.dunnas.chamados_condominio.application.exceptions.BadRequestException;
import com.dunnas.chamados_condominio.application.exceptions.NotFoundException;
import com.dunnas.chamados_condominio.application.gateways.UserGateway;
import com.dunnas.chamados_condominio.domain.entity.User;

public class FindUserByEmail {
    private final UserGateway userGateway;

    public FindUserByEmail(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public User findUserByEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new BadRequestException("Email must not be null or empty");
        }

        User user = userGateway.findUserByEmail(email);

        if (user == null) {
            throw new NotFoundException("User not found");
        }

        return user;
    }
}
