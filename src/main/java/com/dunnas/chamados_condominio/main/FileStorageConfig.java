package com.dunnas.chamados_condominio.main;

import com.dunnas.chamados_condominio.application.gateways.FileStorageGateway;
import com.dunnas.chamados_condominio.infrastructure.gateways.FileStorageRepositoryGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

@Configuration
public class FileStorageConfig {

    @Bean
    FileStorageGateway fileStorageGateway() {
        return new FileStorageRepositoryGateway();
    }
}
