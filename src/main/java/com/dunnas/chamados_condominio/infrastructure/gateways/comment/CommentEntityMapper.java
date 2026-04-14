package com.dunnas.chamados_condominio.infrastructure.gateways.comment;

import com.dunnas.chamados_condominio.domain.entity.Comment;
import com.dunnas.chamados_condominio.infrastructure.persistence.comment.CommentEntity;

public class CommentEntityMapper {
    CommentEntity toEntity(Comment comment) {
        CommentEntity commentEntity = new CommentEntity(comment.getContent(), comment.getCallId());
        commentEntity.setUserId(comment.getUserId());
        commentEntity.setId(comment.getId());
        commentEntity.setCreatedAt(comment.getCreatedAt());
        return commentEntity;
    }

    Comment toDomainObj(CommentEntity commentEntity) {
        Comment commentDomainObj = new Comment(commentEntity.getContent(), commentEntity.getCallId());
        commentEntity.setUserId(commentEntity.getUserId());
        commentDomainObj.setId(commentEntity.getId());
        commentDomainObj.setCreatedAt(commentEntity.getCreatedAt());
        return commentDomainObj;
    }
}
