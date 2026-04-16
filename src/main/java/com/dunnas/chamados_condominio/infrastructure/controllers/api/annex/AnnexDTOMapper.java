package com.dunnas.chamados_condominio.infrastructure.controllers.api.annex;

import com.dunnas.chamados_condominio.application.gateways.CallGateway;
import com.dunnas.chamados_condominio.domain.entity.Annex;
import com.dunnas.chamados_condominio.domain.entity.Call;
import com.dunnas.chamados_condominio.infrastructure.controllers.api.call.CallDTOMapper;
import com.dunnas.chamados_condominio.infrastructure.controllers.api.call.CallResponse;

public class AnnexDTOMapper {
    private final CallDTOMapper callDTOMapper;
    private final CallGateway callGateway;

    public AnnexDTOMapper(CallDTOMapper callDTOMapper, CallGateway callGateway) {
        this.callDTOMapper = callDTOMapper;
        this.callGateway = callGateway;
    }

    public AnnexResponse toResponse(Annex annex) {
        CallResponse callResponse = callDTOMapper.toResponse(annex.getCall());
        return new AnnexResponse(annex.getId(), callResponse, annex.getFileName(), annex.getFilePath());
    }

    public Annex toEntity(AnnexRequest request) {
        Call call = callGateway.findCallById(request.callId());
        return new Annex(call, request.fileName(), request.filePath());
    }
}
