package com.dunnas.chamados_condominio.infrastructure.controllers.call;

import java.time.LocalDateTime;

public record CallRequest(String title, String description, LocalDateTime deadline, Long unitId, Long callTypeId) {
}
