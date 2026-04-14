package com.dunnas.chamados_condominio.application.usecases.call;

import com.dunnas.chamados_condominio.application.gateways.CallGateway;
import com.dunnas.chamados_condominio.application.gateways.CallTypeGateway;
import com.dunnas.chamados_condominio.application.gateways.StatusGateway;
import com.dunnas.chamados_condominio.application.gateways.UserGateway;
import com.dunnas.chamados_condominio.domain.entity.*;

import java.time.LocalDateTime;

public class UpdateCall {
    private final CallGateway callGateway;
    private final StatusGateway statusGateway;
    private final UserGateway userGateway;
    private final CallTypeGateway callTypeGateway;

    public UpdateCall(CallGateway callGateway, StatusGateway statusGateway, UserGateway userGateway, CallTypeGateway callTypeGateway) {
        this.callGateway = callGateway;
        this.statusGateway = statusGateway;
        this.userGateway = userGateway;
        this.callTypeGateway = callTypeGateway;
    }

    public Call updateCall(Long id, Call updatedCall, String loggedUserEmail) {
        Call foundCall = callGateway.findCallById(id);

        if(foundCall == null) {
            throw new RuntimeException("Call not found");
        }

        User loggedUser = userGateway.findUserByEmail(loggedUserEmail);

        if (loggedUser.getRole() == Role.RESIDENT) {
            throw new RuntimeException("Residents cannot update call status");
        }

        if (loggedUser.getRole() == Role.COLLABORATOR) {

            CallType callType = callTypeGateway.findCallTypeById(foundCall.getCallTypeId());

            if (callType == null) {
                throw new RuntimeException("CallType not found");
            }

            if (!callType.getTitle().equalsIgnoreCase(loggedUser.getScope())) {
                throw new RuntimeException("Collaborator outside scope");
            }
        }

        Status currentStatus = statusGateway.findStatusById(foundCall.getStatusId());
        if (currentStatus.isFinal()) {
            throw new RuntimeException("Call already finished and cannot be updated");
        }

        if (updatedCall.getStatusId() != null && !updatedCall.getStatusId().equals(foundCall.getStatusId())) {
            Status newStatus = statusGateway.findStatusById(updatedCall.getStatusId());

            if (newStatus.isFinal()) {
                foundCall.setFinishedAt(LocalDateTime.now());
            }

            foundCall.setStatusId(updatedCall.getStatusId());
        }

        return callGateway.updateCall(foundCall);
    }
}
