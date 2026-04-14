package com.dunnas.chamados_condominio.infrastructure.controllers.comment;

public record CommentRequest(String content, Long callId, Long userId) {
}
