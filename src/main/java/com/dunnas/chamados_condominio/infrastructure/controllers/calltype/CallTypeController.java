package com.dunnas.chamados_condominio.infrastructure.controllers.calltype;

import com.dunnas.chamados_condominio.application.usecases.calltype.CreateCallType;
import com.dunnas.chamados_condominio.domain.entity.CallType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calltypes")
public class CallTypeController {
    private final CreateCallType createCallType;
    private final CallTypeDTOMapper callTypeDTOMapper;

    public CallTypeController(final CreateCallType createCallType, CallTypeDTOMapper callTypeDTOMapper) {
        this.createCallType = createCallType;
        this.callTypeDTOMapper = callTypeDTOMapper;
    }

    @PostMapping
    public CreateCallTypeResponse createCallType(@RequestBody CreateCallTypeRequest request) {
        CallType callType = callTypeDTOMapper.toEntity(request);
        CallType createdCallType = createCallType.createCallType(callType);
        return callTypeDTOMapper.toResponse(createdCallType);
    }

}
