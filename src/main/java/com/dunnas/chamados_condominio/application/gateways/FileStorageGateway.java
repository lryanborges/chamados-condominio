package com.dunnas.chamados_condominio.application.gateways;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageGateway {
    String store(MultipartFile file);
}
