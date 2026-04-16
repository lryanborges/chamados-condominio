package com.dunnas.chamados_condominio.infrastructure.persistence.unit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UnitRepository extends JpaRepository<UnitEntity, Long> {
    @Query("SELECT u FROM UnitEntity u WHERE u.block.id = :blockId")
    List<UnitEntity> findByBlockId(@Param("blockId") Long blockId);

    @Query("""
        SELECT u FROM UnitEntity u 
        JOIN u.users us 
        WHERE us.id = :userId
    """)
    List<UnitEntity> findAllByUserId(@Param("userId") Long userId);
}
