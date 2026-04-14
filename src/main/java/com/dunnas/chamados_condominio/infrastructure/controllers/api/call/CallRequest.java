package com.dunnas.chamados_condominio.infrastructure.controllers.api.call;

import java.time.LocalDateTime;

public record CallRequest(String title, String description, Long unitId, Long callTypeId) {
}
