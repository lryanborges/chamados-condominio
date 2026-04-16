package com.dunnas.chamados_condominio.infrastructure.controllers.api.comment;

import com.dunnas.chamados_condominio.domain.entity.Comment;

public class CommentDTOMapper {
    public CommentResponse toResponse(Comment comment) {
        return new CommentResponse(comment.getId(), comment.getContent(), comment.getCallId(), comment.getUserId(), comment.getCreatedAt());
    }

    public Comment toEntity(CommentRequest request) {
        return new Comment(request.content(), request.callId());
    }
}
