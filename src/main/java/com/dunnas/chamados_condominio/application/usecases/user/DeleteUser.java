package com.dunnas.chamados_condominio.application.usecases.user;

import com.dunnas.chamados_condominio.application.exceptions.BadRequestException;
import com.dunnas.chamados_condominio.application.exceptions.ForbiddenException;
import com.dunnas.chamados_condominio.application.gateways.UserGateway;
import com.dunnas.chamados_condominio.domain.entity.Role;
import com.dunnas.chamados_condominio.domain.entity.User;

public class DeleteUser {
    private final UserGateway userGateway;

    public DeleteUser(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public void deleteUser(Long id, String loggedUserEmail) {
        if (id == null) {
            throw new BadRequestException("User id must not be null");
        }

        User loggedUser = userGateway.findUserByEmail(loggedUserEmail);

        if (loggedUser.getRole() != Role.ADMIN) {
            throw new ForbiddenException("Only admins can delete users");
        }
        if(loggedUser.getId() == id) {
            throw new ForbiddenException("You can't delete yourself");
        }
        userGateway.deleteUser(id);
    }
}
