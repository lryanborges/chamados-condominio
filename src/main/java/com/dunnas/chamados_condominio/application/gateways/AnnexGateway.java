package com.dunnas.chamados_condominio.application.gateways;

import com.dunnas.chamados_condominio.domain.entity.Annex;

import java.util.List;

public interface AnnexGateway {
    Annex createAnnex(Annex annex);
    List<Annex> findAnnexesByCallId(Long callId);
    Annex findAnnexById(Long id);
}
