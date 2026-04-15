package com.dunnas.chamados_condominio.application.usecases.user;

import com.dunnas.chamados_condominio.application.exceptions.BadRequestException;
import com.dunnas.chamados_condominio.application.exceptions.ForbiddenException;
import com.dunnas.chamados_condominio.application.exceptions.NotFoundException;
import com.dunnas.chamados_condominio.application.gateways.UserGateway;
import com.dunnas.chamados_condominio.domain.entity.Role;
import com.dunnas.chamados_condominio.domain.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UpdateUser {
    private final UserGateway userGateway;
    private final PasswordEncoder passwordEncoder;

    public UpdateUser(UserGateway userGateway, PasswordEncoder passwordEncoder) {
        this.userGateway = userGateway;
        this.passwordEncoder = passwordEncoder;
    }

    public User updateUser(Long id, User updatedUser, String loggedUserEmail) {
        if (id == null) {
            throw new BadRequestException("User id must not be null");
        }

        User loggedUser = userGateway.findUserByEmail(loggedUserEmail);

        if (loggedUser.getRole() != Role.ADMIN) {
            throw new ForbiddenException("Only admins can update users");
        }

        User foundUser = userGateway.findUserById(id);
        if (foundUser == null) {
            throw new NotFoundException("User not found");
        }
        if (updatedUser == null) {
            throw new BadRequestException("Updated user data must not be null");
        }

        if(updatedUser.getName() != null && !updatedUser.getName().isBlank()) { foundUser.setName(updatedUser.getName()); }
        if(updatedUser.getEmail() != null && !updatedUser.getEmail().isBlank()) { foundUser.setEmail(updatedUser.getEmail()); }
        if(updatedUser.getPassword() != null && !updatedUser.getPassword().isBlank()) {
            String encriptedPassword = passwordEncoder.encode(updatedUser.getPassword());
            foundUser.setPassword(encriptedPassword);
        }
        if(updatedUser.getRole() != null) { foundUser.setRole(updatedUser.getRole()); }
        if(updatedUser.getScope() != null && !updatedUser.getScope().isBlank()) { foundUser.setScope(updatedUser.getScope()); }

        return userGateway.updateUser(foundUser);
    }
}
