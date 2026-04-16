package com.dunnas.chamados_condominio.infrastructure.controllers.api.comment;

import com.dunnas.chamados_condominio.infrastructure.controllers.api.call.CallResponse;
import com.dunnas.chamados_condominio.infrastructure.controllers.api.user.UserResponse;

import java.time.LocalDateTime;

public record CommentResponse(Long id, String content, CallResponse call, UserResponse userId, LocalDateTime createdAt) {
}
