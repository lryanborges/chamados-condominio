package com.dunnas.chamados_condominio.infrastructure.persistence.call;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CallRepository extends JpaRepository<CallEntity, Long> {

    @Query("""
        SELECT c FROM CallEntity c
        WHERE (:statusId IS NULL OR c.statusId = :statusId)
        AND (:unitId IS NULL OR c.unitId = :unitId)
        AND (:callType IS NULL OR c.callTypeId IN (
            SELECT ct.id FROM CallTypeEntity ct WHERE ct.title = :callType
        ))
    """)
    List<CallEntity> findAllByFilters(
            @Param("statusId") Long statusId,
            @Param("unitId") Long unitId,
            @Param("callType") String callType
    );
}
