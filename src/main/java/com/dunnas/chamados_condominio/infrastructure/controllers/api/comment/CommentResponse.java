package com.dunnas.chamados_condominio.infrastructure.controllers.api.comment;

import java.time.LocalDateTime;

public record CommentResponse(Long id, String content, Long callId, Long userId, LocalDateTime createdAt) {
}
