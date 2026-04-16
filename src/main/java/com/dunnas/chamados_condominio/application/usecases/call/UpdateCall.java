package com.dunnas.chamados_condominio.application.usecases.call;

import com.dunnas.chamados_condominio.application.exceptions.BadRequestException;
import com.dunnas.chamados_condominio.application.exceptions.ForbiddenException;
import com.dunnas.chamados_condominio.application.exceptions.NotFoundException;
import com.dunnas.chamados_condominio.application.gateways.CallGateway;
import com.dunnas.chamados_condominio.application.gateways.CallTypeGateway;
import com.dunnas.chamados_condominio.application.gateways.StatusGateway;
import com.dunnas.chamados_condominio.application.gateways.UserGateway;
import com.dunnas.chamados_condominio.domain.entity.*;

import java.time.LocalDateTime;

public class UpdateCall {
    private final CallGateway callGateway;
    private final UserGateway userGateway;

    public UpdateCall(CallGateway callGateway, UserGateway userGateway) {
        this.callGateway = callGateway;
        this.userGateway = userGateway;
    }

    public Call updateCall(Long id, Call updatedCall, String loggedUserEmail) {
        Call foundCall = callGateway.findCallById(id);

        if(foundCall == null) {
            throw new NotFoundException("Call not found");
        }

        User loggedUser = userGateway.findUserByEmail(loggedUserEmail);

        if (loggedUser.getRole() == Role.RESIDENT) {
            throw new ForbiddenException("Residents cannot update call status");
        }

        if (loggedUser.getRole() == Role.COLLABORATOR) {

            CallType callType = foundCall.getCallType();

            if (!callType.getTitle().equalsIgnoreCase(loggedUser.getScope())) {
                throw new ForbiddenException("Collaborator outside scope");
            }
        }

        if (foundCall.getStatus().getIsFinal()) {
            throw new BadRequestException("Call already finished and cannot be updated");
        }

        if (updatedCall.getStatus() != null && !updatedCall.getStatus().getId().equals(foundCall.getStatus().getId())) {
            Status newStatus = updatedCall.getStatus();

            if (newStatus == null) {
                throw new NotFoundException("Status not found");
            }

            if (newStatus.getIsFinal()) {
                foundCall.setFinishedAt(LocalDateTime.now());
            }

            foundCall.setStatus(updatedCall.getStatus());
        }

        return callGateway.updateCall(foundCall);
    }
}
