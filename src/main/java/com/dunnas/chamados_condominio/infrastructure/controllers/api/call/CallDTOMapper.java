package com.dunnas.chamados_condominio.infrastructure.controllers.api.call;

import com.dunnas.chamados_condominio.application.gateways.CallTypeGateway;
import com.dunnas.chamados_condominio.application.gateways.UnitGateway;
import com.dunnas.chamados_condominio.domain.entity.Call;
import com.dunnas.chamados_condominio.domain.entity.CallType;
import com.dunnas.chamados_condominio.domain.entity.Unit;

public class CallDTOMapper {
    private final UnitGateway unitGateway;
    private final CallTypeGateway callTypeGateway;

    public CallDTOMapper(UnitGateway unitGateway, CallTypeGateway callTypeGateway) {
        this.unitGateway = unitGateway;
        this.callTypeGateway = callTypeGateway;
    }

    public CallResponse toResponse(Call call) {
        return new CallResponse(call.getId(), call.getTitle(), call.getDescription(), call.getDeadline(), call.getUser(), call.getUnit(), call.getStatus(), call.getCallType(), call.getCreatedAt(), call.getFinishedAt());
    }

    public Call toEntity(CallRequest request){
        Unit unit = unitGateway.findUnitById(request.unitId());
        CallType callType = callTypeGateway.findCallTypeById(request.callTypeId());
        return new Call(request.title(), request.description(), unit, callType);
    }

    public Call toEntityUpdate(UpdateCallRequest request){
        return new Call(request.status());
    }
}
