package com.dunnas.chamados_condominio.application.usecases.user;

import com.dunnas.chamados_condominio.application.gateways.UserGateway;
import com.dunnas.chamados_condominio.domain.entity.Role;
import com.dunnas.chamados_condominio.domain.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CreateUser {
    private final UserGateway userGateway;
    private final PasswordEncoder passwordEncoder;

    public CreateUser(UserGateway userGateway, PasswordEncoder passwordEncoder) {
        this.userGateway = userGateway;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(User newUser, String loggedUserEmail) {
        User loggedUser = userGateway.findUserByEmail(loggedUserEmail);

        if (loggedUser.getRole() != Role.ADMIN) {
            throw new RuntimeException("Only admins can create users");
        }

        String encriptedPassword = passwordEncoder.encode(newUser.getPassword());
        newUser.setPassword(encriptedPassword);
        return userGateway.createUser(newUser);
    }
}
