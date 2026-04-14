package com.dunnas.chamados_condominio.application.usecases.user;

import com.dunnas.chamados_condominio.application.gateways.UserGateway;
import com.dunnas.chamados_condominio.domain.entity.Role;
import com.dunnas.chamados_condominio.domain.entity.User;

public class DeleteUser {
    private final UserGateway userGateway;

    public DeleteUser(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public void deleteUser(Long id, String loggedUserEmail) {
        User loggedUser = userGateway.findUserByEmail(loggedUserEmail);

        if (loggedUser.getRole() != Role.ADMIN) {
            throw new RuntimeException("Only admins can delete users");
        }
        userGateway.deleteUser(id);
    }
}
