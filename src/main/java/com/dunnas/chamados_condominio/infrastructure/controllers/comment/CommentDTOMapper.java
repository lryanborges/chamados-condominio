package com.dunnas.chamados_condominio.infrastructure.controllers.comment;

import com.dunnas.chamados_condominio.domain.entity.Comment;

public class CommentDTOMapper {
    CommentResponse toResponse(Comment comment) {
        return new CommentResponse(comment.getId(), comment.getContent(), comment.getCallId(), comment.getUserId(), comment.getCreatedAt());
    }

    Comment toEntity(CommentRequest request) {
        return new Comment(request.content(), request.callId(), request.userId());
    }
}
