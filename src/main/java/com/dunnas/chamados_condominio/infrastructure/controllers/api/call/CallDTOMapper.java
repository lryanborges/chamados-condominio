package com.dunnas.chamados_condominio.infrastructure.controllers.api.call;

import com.dunnas.chamados_condominio.domain.entity.Call;

public class CallDTOMapper {
    public CallResponse toResponse(Call call) {
        return new CallResponse(call.getId(), call.getTitle(), call.getDescription(), call.getDeadline(), call.getUserId(), call.getUnitId(), call.getStatusId(), call.getCallTypeId(), call.getCreatedAt(), call.getFinishedAt());
    }

    public Call toEntity(CallRequest request){
        return new Call(request.title(), request.description(), request.unitId(), request.callTypeId());
    }

    public Call toEntityUpdate(UpdateCallRequest request){
        return new Call(request.statusId());
    }
}
