package com.dunnas.chamados_condominio.application.usecases.comment;

import com.dunnas.chamados_condominio.application.gateways.CommentGateway;
import com.dunnas.chamados_condominio.domain.entity.Comment;

import java.util.List;

public class FindCommentsByCallId {
    private final CommentGateway commentGateway;

    public FindCommentsByCallId(final CommentGateway commentGateway) {
        this.commentGateway = commentGateway;
    }

    public List<Comment> findCommentsByCallId(Long callId) {
        return commentGateway.findCommentsByCallId(callId);
    }
}
