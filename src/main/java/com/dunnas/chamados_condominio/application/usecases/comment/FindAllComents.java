package com.dunnas.chamados_condominio.application.usecases.comment;

import com.dunnas.chamados_condominio.application.gateways.CommentGateway;
import com.dunnas.chamados_condominio.domain.entity.Comment;

import java.util.List;

public class FindAllComents {
    private CommentGateway commentGateway;

    public FindAllComents(CommentGateway commentGateway) {
        this.commentGateway = commentGateway;
    }

    public List<Comment> findAllComments() {
        return commentGateway.findAllComments();
    }
}
