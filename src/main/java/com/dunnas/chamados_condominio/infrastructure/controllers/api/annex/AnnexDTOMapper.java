package com.dunnas.chamados_condominio.infrastructure.controllers.api.annex;

import com.dunnas.chamados_condominio.domain.entity.Annex;

public class AnnexDTOMapper {
    public AnnexResponse toResponse(Annex annex) {
        return new AnnexResponse(annex.getId(), annex.getCallId(), annex.getFileName(), annex.getFilePath());
    }

    public Annex toEntity(AnnexRequest request) {
        return new Annex(request.callId(), request.fileName(), request.filePath());
    }
}
