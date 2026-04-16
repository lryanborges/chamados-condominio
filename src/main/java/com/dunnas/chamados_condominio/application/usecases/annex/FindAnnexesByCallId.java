package com.dunnas.chamados_condominio.application.usecases.annex;

import com.dunnas.chamados_condominio.application.gateways.AnnexGateway;
import com.dunnas.chamados_condominio.domain.entity.Annex;

import java.util.List;

public class FindAnnexesByCallId {
    private final AnnexGateway annexGateway;

    public FindAnnexesByCallId(final AnnexGateway annexGateway) {
        this.annexGateway = annexGateway;
    }

    public List<Annex> findAnnexByCallId(Long callId) {
        return annexGateway.findAnnexesByCallId(callId);
    }
}
