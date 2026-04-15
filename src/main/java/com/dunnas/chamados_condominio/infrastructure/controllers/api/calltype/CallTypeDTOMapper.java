package com.dunnas.chamados_condominio.infrastructure.controllers.api.calltype;

import com.dunnas.chamados_condominio.domain.entity.CallType;

public class CallTypeDTOMapper {
    public CreateCallTypeResponse toResponse(CallType callType) {
        return new CreateCallTypeResponse(callType.getId(), callType.getTitle(), callType.getDeadline());
    }

    public CallType toEntity(CreateCallTypeRequest request) {
        return new CallType(request.title(), request.deadline());
    }
}
