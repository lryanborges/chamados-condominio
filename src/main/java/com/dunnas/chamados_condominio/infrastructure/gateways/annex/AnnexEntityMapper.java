package com.dunnas.chamados_condominio.infrastructure.gateways.annex;

import com.dunnas.chamados_condominio.domain.entity.Annex;
import com.dunnas.chamados_condominio.domain.entity.Call;
import com.dunnas.chamados_condominio.infrastructure.gateways.call.CallEntityMapper;
import com.dunnas.chamados_condominio.infrastructure.persistence.annex.AnnexEntity;
import com.dunnas.chamados_condominio.infrastructure.persistence.call.CallEntity;

import java.util.List;

public class AnnexEntityMapper {
    private final CallEntityMapper callEntityMapper;

    public AnnexEntityMapper(CallEntityMapper callEntityMapper) {
        this.callEntityMapper = callEntityMapper;
    }

    public AnnexEntity toEntity(Annex annex) {
        CallEntity callEntity = callEntityMapper.toEntity(annex.getCall());
        return new AnnexEntity(callEntity, annex.getFileName(), annex.getFilePath());
    }

    public Annex toDomainObj(AnnexEntity annexEntity) {
        Call call = callEntityMapper.toDomainObj(annexEntity.getCall());
        return new Annex(call, annexEntity.getFileName(), annexEntity.getFilePath());
    }

    public List<Annex> toDomainObjList(List<AnnexEntity> annexEntityList) {
        return annexEntityList.stream()
                .map(this::toDomainObj)
                .toList();
    }

    public List<AnnexEntity> toEntityList(List<Annex> annexList) {
        return annexList.stream()
                .map(this::toEntity)
                .toList();
    }
}
