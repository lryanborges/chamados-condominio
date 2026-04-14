package com.dunnas.chamados_condominio.application.usecases.user;

import com.dunnas.chamados_condominio.application.exceptions.BadRequestException;
import com.dunnas.chamados_condominio.application.exceptions.ForbiddenException;
import com.dunnas.chamados_condominio.application.exceptions.NotFoundException;
import com.dunnas.chamados_condominio.application.gateways.UnitGateway;
import com.dunnas.chamados_condominio.application.gateways.UserGateway;
import com.dunnas.chamados_condominio.domain.entity.Role;
import com.dunnas.chamados_condominio.domain.entity.Unit;
import com.dunnas.chamados_condominio.domain.entity.User;

import java.util.List;

public class LinkUserToUnit {
    private final UserGateway userGateway;
    private final UnitGateway unitGateway;

    public LinkUserToUnit(UserGateway userGateway, UnitGateway unitGateway) {
        this.userGateway = userGateway;
        this.unitGateway = unitGateway;
    }

    public void link(Long userId, List<Long> unitIds, String loggedUserEmail) {
        if (userId == null) {
            throw new BadRequestException("User id must not be null");
        }

        if (unitIds == null || unitIds.isEmpty()) {
            throw new BadRequestException("Unit ids must not be null or empty");
        }


        User loggedUser = userGateway.findUserByEmail(loggedUserEmail);

        if (loggedUser.getRole() != Role.ADMIN) {
            throw new ForbiddenException("Only admins can link users to units");
        }

        User user = userGateway.findUserById(userId);

        if (user == null) {
            throw new NotFoundException("User not found");
        }

        unitIds.forEach(unitId -> {
            Unit unit = unitGateway.findUnitById(unitId);
            if (unit == null) {
                throw new NotFoundException("Unit not found: " + unitId);
            }
            userGateway.linkUserToUnit(user.getId(), unit);
        });
    }
}
