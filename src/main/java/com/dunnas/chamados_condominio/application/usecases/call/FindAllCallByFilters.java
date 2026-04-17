package com.dunnas.chamados_condominio.application.usecases.call;

import com.dunnas.chamados_condominio.application.exceptions.NotFoundException;
import com.dunnas.chamados_condominio.application.gateways.CallGateway;
import com.dunnas.chamados_condominio.application.gateways.UserGateway;
import com.dunnas.chamados_condominio.domain.entity.Call;
import com.dunnas.chamados_condominio.domain.entity.Role;
import com.dunnas.chamados_condominio.domain.entity.User;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        if (user.getRole() == Role.ADMIN) {
            return callGateway.findAllCallByFilters(statusId, null, null);
        }

        if (user.getRole() == Role.RESIDENT) {
            return callGateway.findAllCallByFilters(statusId, user.getUnitIds(), null);
        }

        if (user.getRole() == Role.COLLABORATOR) {
            List<Call> byCallType = callGateway.findAllCallByFilters(statusId, null, user.getScope());
            List<Call> byUnitIds = callGateway.findAllCallByFilters(statusId, user.getUnitIds(), null);

            return Stream.concat(byCallType.stream(), byUnitIds.stream())
                    .collect(Collectors.toMap(Call::getId, c -> c, (a, b) -> a))
                    .values()
                    .stream()
                    .sorted(Comparator.comparing(Call::getId))
                    .toList();
        }

        return callGateway.findAllCallByFilters(statusId, unitIds, callType);
    }
}
