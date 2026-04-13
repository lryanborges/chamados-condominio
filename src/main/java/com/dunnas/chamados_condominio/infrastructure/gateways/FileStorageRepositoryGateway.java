package com.dunnas.chamados_condominio.infrastructure.gateways;

import com.dunnas.chamados_condominio.application.gateways.FileStorageGateway;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class FileStorageRepositoryGateway implements FileStorageGateway {
    private final Path storageLocation = Paths.get("/app/uploads");

    @Override
    public String store(MultipartFile file) {
        try {
            Files.createDirectories(storageLocation);
            Path destination = storageLocation.resolve(
                    UUID.randomUUID() + "_" + file.getOriginalFilename()
            );
            file.transferTo(destination);
            return destination.toString();
        } catch (IOException e) {
            throw new RuntimeException("Falha ao salvar arquivo", e);
        }
    }
}
