package com.dunnas.chamados_condominio.application.gateways;

import com.dunnas.chamados_condominio.domain.entity.Comment;

import java.util.List;

public interface CommentGateway {
    Comment createComment(Comment comment);
    List<Comment> findAllComments();
}
