package com.dunnas.chamados_condominio.infrastructure.controllers.comment;

import java.time.LocalDateTime;

public record CommentResponse(Long id, String content, Long callId, Long userId, LocalDateTime createdAt) {
}
