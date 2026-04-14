package com.dunnas.chamados_condominio.infrastructure.controllers.call;

import com.dunnas.chamados_condominio.application.usecases.call.CreateCall;
import com.dunnas.chamados_condominio.application.usecases.call.FindAllCallByFilters;
import com.dunnas.chamados_condominio.application.usecases.call.FindCallById;
import com.dunnas.chamados_condominio.application.usecases.call.UpdateCall;
import com.dunnas.chamados_condominio.domain.entity.Call;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("calls")
public class CallController {
    private final CreateCall createCall;
    private final FindAllCallByFilters findAllCallByFilters;
    private final FindCallById findCallById;
    private final UpdateCall updateCall;
    private final CallDTOMapper callDTOMapper;

    public CallController(CreateCall createCall, FindAllCallByFilters findAllCallByFilters, FindCallById findCallById, UpdateCall updateCall, CallDTOMapper callDTOMapper) {
        this.createCall = createCall;
        this.findAllCallByFilters = findAllCallByFilters;
        this.findCallById = findCallById;
        this.updateCall = updateCall;
        this.callDTOMapper = callDTOMapper;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    CallResponse createCall(@RequestPart("data") CallRequest request, @RequestPart(value = "files", required = false) List<MultipartFile> annexes) {
        String loggedUserEmail = SecurityContextHolder.getContext()
                .getAuthentication().getName();
        Call call = callDTOMapper.toEntity(request);
        Call createdCall = createCall.createCall(call, annexes, loggedUserEmail);
        return callDTOMapper.toResponse(createdCall);
    }

    @GetMapping
    List<CallResponse> findAllCalls(
            @RequestParam(required = false) Long statusId
    ) {
        String userEmail = SecurityContextHolder.getContext()
                .getAuthentication().getName();

        List<Call> calls = findAllCallByFilters.findAllCallByFilters(userEmail, statusId);
        return calls.stream().map(callDTOMapper::toResponse).toList();
    }

    @GetMapping("/{id}")
    CallResponse findCallById(@PathVariable Long id) {
        Call call = findCallById.findCallById(id);
        return callDTOMapper.toResponse(call);
    }

    @PatchMapping("/{id}")
    CallResponse updateCall(@PathVariable Long id, @RequestBody UpdateCallRequest request) {
        String loggedUserEmail = SecurityContextHolder.getContext()
                .getAuthentication().getName();
        Call call = callDTOMapper.toEntityUpdate(request);
        Call updatedCall = updateCall.updateCall(id, call, loggedUserEmail);
        return callDTOMapper.toResponse(updatedCall);
    }

}
