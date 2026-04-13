package com.dunnas.chamados_condominio.infrastructure.controllers.call;

import com.dunnas.chamados_condominio.application.usecases.call.CreateCall;
import com.dunnas.chamados_condominio.domain.entity.Call;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("calls")
public class CallController {
    private final CreateCall createCall;
    private final CallDTOMapper callDTOMapper;

    public CallController(CreateCall createCall, CallDTOMapper callDTOMapper) {
        this.createCall = createCall;
        this.callDTOMapper = callDTOMapper;
    }

    @PostMapping
    CallResponse createCall(@RequestBody CallRequest request) {
        Call call = callDTOMapper.toEntity(request);
        Call createdCall = createCall.createCall(call);
        return callDTOMapper.toResponse(createdCall);
    }
}
