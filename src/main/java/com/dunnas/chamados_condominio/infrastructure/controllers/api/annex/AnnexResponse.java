package com.dunnas.chamados_condominio.infrastructure.controllers.api.annex;

import com.dunnas.chamados_condominio.infrastructure.controllers.api.call.CallResponse;

public record AnnexResponse(Long id, CallResponse call, String fileName, String filePath) {
}
