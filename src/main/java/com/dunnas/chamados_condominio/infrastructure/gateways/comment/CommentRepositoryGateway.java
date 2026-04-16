package com.dunnas.chamados_condominio.infrastructure.gateways.comment;

import com.dunnas.chamados_condominio.application.gateways.CommentGateway;
import com.dunnas.chamados_condominio.domain.entity.Comment;
import com.dunnas.chamados_condominio.infrastructure.persistence.comment.CommentEntity;
import com.dunnas.chamados_condominio.infrastructure.persistence.comment.CommentRepository;

import java.util.List;

public class CommentRepositoryGateway implements CommentGateway {

    private final CommentRepository repository;
    private final CommentEntityMapper mapper;

    public CommentRepositoryGateway(CommentRepository repository, CommentEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Comment createComment(Comment comment) {
        CommentEntity commentEntity = mapper.toEntity(comment);
        CommentEntity savedComment = repository.save(commentEntity);
        return mapper.toDomainObj(savedComment);
    }

    @Override
    public List<Comment> findAllComments() {
        List<CommentEntity> commentEntities = repository.findAll();
        return mapper.toDomainObjList(commentEntities);
    }
}
