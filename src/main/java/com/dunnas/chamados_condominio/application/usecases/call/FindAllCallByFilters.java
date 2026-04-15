package com.dunnas.chamados_condominio.application.usecases.call;

import com.dunnas.chamados_condominio.application.exceptions.NotFoundException;
import com.dunnas.chamados_condominio.application.gateways.CallGateway;
import com.dunnas.chamados_condominio.application.gateways.UserGateway;
import com.dunnas.chamados_condominio.domain.entity.Call;
import com.dunnas.chamados_condominio.domain.entity.Role;
import com.dunnas.chamados_condominio.domain.entity.User;

import java.util.List;

public class FindAllCallByFilters {
    private final CallGateway callGateway;
    private final UserGateway userGateway;

    public FindAllCallByFilters(CallGateway callGateway, UserGateway userGateway) {
        this.callGateway = callGateway;
        this.userGateway = userGateway;
    }

    public List<Call> findAllCallByFilters(String userEmail, Long statusId) {
        User user = userGateway.findUserByEmail(userEmail);

        if (user == null) {
            throw new NotFoundException("User not found");
        }

        String callType = null;
        List<Long> unitIds = null;

        if (user.getRole() == Role.COLLABORATOR) {
            callType = user.getScope();
        } else if (user.getRole() == Role.RESIDENT) {
            unitIds = user.getUnitIds();
        }

        return callGateway.findAllCallByFilters(statusId, unitIds, callType);
    }
}
