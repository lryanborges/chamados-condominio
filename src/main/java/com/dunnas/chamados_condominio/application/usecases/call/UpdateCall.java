package com.dunnas.chamados_condominio.application.usecases.call;

import com.dunnas.chamados_condominio.application.gateways.CallGateway;
import com.dunnas.chamados_condominio.application.gateways.StatusGateway;
import com.dunnas.chamados_condominio.domain.entity.Call;
import com.dunnas.chamados_condominio.domain.entity.Status;

import java.time.LocalDateTime;

public class UpdateCall {
    private final CallGateway callGateway;
    private final StatusGateway statusGateway;

    public UpdateCall(CallGateway callGateway, StatusGateway statusGateway) {
        this.callGateway = callGateway;
        this.statusGateway = statusGateway;
    }

    public Call updateCall(Long id, Call updatedCall) {
        Call foundCall = callGateway.findCallById(id);

        if(foundCall == null) {
            return null;
        }

        Status currentStatus = statusGateway.findStatusById(foundCall.getStatusId());
        if (currentStatus.isFinal()) {
            throw new RuntimeException("Chamado já finalizado, não pode ser atualizado");
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
