package com.dunnas.chamados_condominio.infrastructure.controllers.call;

import com.dunnas.chamados_condominio.domain.entity.Call;

public class CallDTOMapper {
    CallResponse toResponse(Call call) {
        return new CallResponse(call.getId(), call.getTitle(), call.getDescription(), call.getDeadline(), call.getUserId(), call.getUnitId(), call.getStatusId(), call.getCallTypeId(), call.getCreatedAt(), call.getFinishedAt());
    }

    Call toEntity(CallRequest request){
        return new Call(request.title(), request.description(), request.deadline(), request.unitId(), request.callTypeId());
    }
}
