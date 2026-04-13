package com.dunnas.chamados_condominio.infrastructure.controllers.call;

import java.time.LocalDateTime;

public record CallResponse(Long id, String title, String description, LocalDateTime deadline, Long userId, Long unitId, Long statusId, Long callTypeId, LocalDateTime createdAt, LocalDateTime finishedAt){
}
