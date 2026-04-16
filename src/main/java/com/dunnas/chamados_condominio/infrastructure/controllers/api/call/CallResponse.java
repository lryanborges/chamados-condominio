package com.dunnas.chamados_condominio.infrastructure.controllers.api.call;

import com.dunnas.chamados_condominio.domain.entity.CallType;
import com.dunnas.chamados_condominio.domain.entity.Status;
import com.dunnas.chamados_condominio.domain.entity.Unit;
import com.dunnas.chamados_condominio.domain.entity.User;

import java.time.LocalDateTime;

public record CallResponse(Long id, String title, String description, LocalDateTime deadline, User user, Unit unit, Status status, CallType callType, LocalDateTime createdAt, LocalDateTime finishedAt){
}
