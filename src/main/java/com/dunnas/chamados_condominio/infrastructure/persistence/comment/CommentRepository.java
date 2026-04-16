package com.dunnas.chamados_condominio.infrastructure.persistence.comment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findAllByCallIdOrderByIdAsc(Long callId);
}
