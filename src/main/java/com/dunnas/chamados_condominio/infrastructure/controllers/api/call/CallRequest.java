package com.dunnas.chamados_condominio.infrastructure.controllers.api.call;

public record CallRequest(String title, String description, Long unitId, Long callTypeId) {
}
