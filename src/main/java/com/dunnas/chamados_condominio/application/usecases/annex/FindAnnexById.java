package com.dunnas.chamados_condominio.application.usecases.annex;

import com.dunnas.chamados_condominio.application.gateways.AnnexGateway;
import com.dunnas.chamados_condominio.domain.entity.Annex;

public class FindAnnexById {
    private final AnnexGateway annexGateway;

    public FindAnnexById(final AnnexGateway annexGateway) {
        this.annexGateway = annexGateway;
    }

    public Annex findAnnexById(Long id) {
        return annexGateway.findAnnexById(id);
    }
}
