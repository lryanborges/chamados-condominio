package com.dunnas.chamados_condominio.application.usecases.call;

import com.dunnas.chamados_condominio.application.gateways.AnnexGateway;
import com.dunnas.chamados_condominio.application.gateways.CallGateway;
import com.dunnas.chamados_condominio.domain.entity.Annex;
import com.dunnas.chamados_condominio.domain.entity.Call;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class CreateCall {
    private final CallGateway callGateway;
    private final AnnexGateway annexGateway;

    public CreateCall(CallGateway callGateway, AnnexGateway annexGateway) {
        this.callGateway = callGateway;
        this.annexGateway = annexGateway;
    }

    public Call createCall(Call call) {
        call.setCreatedAt(LocalDateTime.now());
/*
        if (annexes != null) {
            for (MultipartFile file : annexes) {
                try {
                    String fileName = file.getOriginalFilename();

                    String uniqueName = UUID.randomUUID() + "_" + fileName;

                    String filePath = "uploads/" + uniqueName;

                    Path path = Paths.get(filePath);
                    Files.createDirectories(path.getParent());
                    Files.write(path, file.getBytes());

                    Annex annex = new Annex();
                    annex.setFileName(fileName);
                    annex.setFilePath(filePath);
                    annex.setCallId(savedCall.getId());

                    annexGateway.save(annex);

                } catch (IOException e) {
                    throw new RuntimeException("Erro ao salvar arquivo");
                }
            }
        } */

        return callGateway.createCall(call);
    }
}
