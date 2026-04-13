package com.dunnas.chamados_condominio.application.usecases.call;

import com.dunnas.chamados_condominio.application.gateways.AnnexGateway;
import com.dunnas.chamados_condominio.application.gateways.CallGateway;
import com.dunnas.chamados_condominio.application.gateways.FileStorageGateway;
import com.dunnas.chamados_condominio.domain.entity.Annex;
import com.dunnas.chamados_condominio.domain.entity.Call;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

public class CreateCall {
    private final CallGateway callGateway;
    private final AnnexGateway annexGateway;
    private final FileStorageGateway fileStorageGateway;

    public CreateCall(CallGateway callGateway, AnnexGateway annexGateway, FileStorageGateway fileStorageGateway) {
        this.callGateway = callGateway;
        this.annexGateway = annexGateway;
        this.fileStorageGateway = fileStorageGateway;
    }

    public Call createCall(Call call, List<MultipartFile> annexes) {
        call.setCreatedAt(LocalDateTime.now());
        Call createdCall = callGateway.createCall(call);

        annexes.stream().forEach(file -> {
            String filePath = fileStorageGateway.store(file);

            Annex annex = new Annex(createdCall.getId(), file.getOriginalFilename(), filePath);
            annexGateway.createAnnex(annex);
        });

        return createdCall;
    }
}
