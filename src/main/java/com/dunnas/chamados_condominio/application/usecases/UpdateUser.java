package com.dunnas.chamados_condominio.application.usecases;

import com.dunnas.chamados_condominio.application.gateways.UserGateway;
import com.dunnas.chamados_condominio.domain.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UpdateUser {
    private final UserGateway userGateway;
    private final PasswordEncoder passwordEncoder;

    public UpdateUser(UserGateway userGateway, PasswordEncoder passwordEncoder) {
        this.userGateway = userGateway;
        this.passwordEncoder = passwordEncoder;
    }

    public User updateUser(Long id, User updatedUser) {
        User foundUser = userGateway.findUserById(id);
        if(updatedUser.getName() != null) { foundUser.setName(updatedUser.getName()); }
        if(updatedUser.getEmail() != null) { foundUser.setEmail(updatedUser.getEmail()); }
        if(updatedUser.getPassword() != null) {
            String encriptedPassword = passwordEncoder.encode(updatedUser.getPassword());
            foundUser.setPassword(encriptedPassword);
        }
        if(updatedUser.getRole() != null) { foundUser.setRole(updatedUser.getRole()); }
        if(updatedUser.getScope() != null) { foundUser.setScope(updatedUser.getScope()); }

        return userGateway.updateUser(foundUser);
    }
}
