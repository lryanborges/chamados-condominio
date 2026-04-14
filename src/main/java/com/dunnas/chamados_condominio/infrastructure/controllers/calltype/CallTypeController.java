package com.dunnas.chamados_condominio.infrastructure.controllers.calltype;

import com.dunnas.chamados_condominio.application.usecases.calltype.CreateCallType;
import com.dunnas.chamados_condominio.application.usecases.calltype.FindCallTypeById;
import com.dunnas.chamados_condominio.domain.entity.CallType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calltypes")
public class CallTypeController {
    private final CreateCallType createCallType;
    private final FindCallTypeById findCallTypeById;
    private final CallTypeDTOMapper callTypeDTOMapper;

    public CallTypeController(final CreateCallType createCallType, FindCallTypeById findCallTypeById, CallTypeDTOMapper callTypeDTOMapper) {
        this.createCallType = createCallType;
        this.findCallTypeById = findCallTypeById;
        this.callTypeDTOMapper = callTypeDTOMapper;
    }

    @PostMapping
    public CreateCallTypeResponse createCallType(@RequestBody CreateCallTypeRequest request) {
        CallType callType = callTypeDTOMapper.toEntity(request);
        CallType createdCallType = createCallType.createCallType(callType);
        return callTypeDTOMapper.toResponse(createdCallType);
    }

    @GetMapping("/{id}")
    public CreateCallTypeResponse findCallTypeById(@PathVariable Long id) {
        CallType callType = findCallTypeById.findCallTypeById(id);
        return callTypeDTOMapper.toResponse(callType);
    }

}
