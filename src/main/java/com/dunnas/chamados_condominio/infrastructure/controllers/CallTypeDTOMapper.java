package com.dunnas.chamados_condominio.infrastructure.controllers;

import com.dunnas.chamados_condominio.domain.entity.CallType;

public class CallTypeDTOMapper {
    CreateCallTypeResponse toResponse(CallType callType) {
        return new CreateCallTypeResponse(callType.getId(), callType.getTitle(), callType.getDeadline());
    }

    CallType toEntity(CreateCallTypeRequest request) {
        return new CallType(request.title(), request.deadline());
    }
}
