package com.dunnas.chamados_condominio.infrastructure.persistence.call;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CallRepository extends JpaRepository<CallEntity, Long> {

    @Query("""
    SELECT c FROM CallEntity c
    LEFT JOIN FETCH c.status
    LEFT JOIN FETCH c.callType
    WHERE (:statusId IS NULL OR c.status.id = :statusId)
    AND (:unitIds IS NULL OR c.unit.id IN :unitIds)
    AND (:callType IS NULL OR c.callType.id IN (
        SELECT ct.id FROM CallTypeEntity ct 
        WHERE LOWER(ct.title) = LOWER(CAST(:callType AS string))
    ))
    ORDER BY c.id ASC
    """)
    List<CallEntity> findAllByFilters(
            @Param("statusId") Long statusId,
            @Param("unitIds") List<Long> unitIds,
            @Param("callType") String callType
    );
}