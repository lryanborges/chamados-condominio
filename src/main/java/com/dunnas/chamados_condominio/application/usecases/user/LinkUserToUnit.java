package com.dunnas.chamados_condominio.application.usecases.user;

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
        User loggedUser = userGateway.findUserByEmail(loggedUserEmail);

        if (loggedUser.getRole() != Role.ADMIN) {
            throw new RuntimeException("Only admins can link users to units");
        }

        User user = userGateway.findUserById(userId);

        unitIds.forEach(unitId -> {
            Unit unit = unitGateway.findUnitById(unitId);
            userGateway.linkUserToUnit(user.getId(), unit);
        });
    }
}
