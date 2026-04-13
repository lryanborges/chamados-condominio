package com.dunnas.chamados_condominio.infrastructure.gateways.annex;

import com.dunnas.chamados_condominio.domain.entity.Annex;
import com.dunnas.chamados_condominio.infrastructure.persistence.annex.AnnexEntity;

public class AnnexEntityMapper {
    public AnnexEntity toEntity(Annex annex) {
        return new AnnexEntity(annex.getCallId(), annex.getFileName(), annex.getFilePath());
    }

    public Annex toDomainObj(AnnexEntity annexEntity) {
        return new Annex(annexEntity.getCallId(), annexEntity.getFileName(), annexEntity.getFilePath());
    }
}
