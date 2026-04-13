package com.dunnas.chamados_condominio.infrastructure.gateways.annex;

import com.dunnas.chamados_condominio.application.gateways.AnnexGateway;
import com.dunnas.chamados_condominio.domain.entity.Annex;
import com.dunnas.chamados_condominio.infrastructure.persistence.annex.AnnexEntity;
import com.dunnas.chamados_condominio.infrastructure.persistence.annex.AnnexRepository;

public class AnnexRepositoryGateway implements AnnexGateway {

    private final AnnexRepository repository;
    private final AnnexEntityMapper mapper;

    public AnnexRepositoryGateway(AnnexRepository repository, AnnexEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Annex createAnnex(Annex annex) {
        AnnexEntity annexEntity = mapper.toEntity(annex);
        AnnexEntity savedAnnex = repository.save(annexEntity);
        return mapper.toDomainObj(savedAnnex);
    }
}
