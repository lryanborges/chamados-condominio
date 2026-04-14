package com.dunnas.chamados_condominio.application.gateways;

import com.dunnas.chamados_condominio.domain.entity.Comment;

public interface CommentGateway {
    Comment createComment(Comment comment);
}
