package com.dunnas.chamados_condominio.application.usecases.call;

import com.dunnas.chamados_condominio.application.gateways.AnnexGateway;
import com.dunnas.chamados_condominio.application.gateways.CallGateway;
import com.dunnas.chamados_condominio.application.gateways.FileStorageGateway;
import com.dunnas.chamados_condominio.application.gateways.UserGateway;
import com.dunnas.chamados_condominio.domain.entity.Annex;
import com.dunnas.chamados_condominio.domain.entity.Call;
import com.dunnas.chamados_condominio.domain.entity.Role;
import com.dunnas.chamados_condominio.domain.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

public class CreateCall {
    private final CallGateway callGateway;
    private final AnnexGateway annexGateway;
    private final FileStorageGateway fileStorageGateway;
    private final UserGateway userGateway;

    public CreateCall(CallGateway callGateway, AnnexGateway annexGateway, FileStorageGateway fileStorageGateway, UserGateway userGateway) {
        this.callGateway = callGateway;
        this.annexGateway = annexGateway;
        this.fileStorageGateway = fileStorageGateway;
        this.userGateway = userGateway;
    }

    public Call createCall(Call call, List<MultipartFile> annexes, String loggedUserEmail) {
        User loggedUser = userGateway.findUserByEmail(loggedUserEmail);

        System.out.println("User units: " + loggedUser.getUnitIds());
        System.out.println("Call unit: " + call.getUnitId());

        if (loggedUser.getRole() == Role.RESIDENT) {
            boolean belongsToUnit = loggedUser.getUnitIds().contains(call.getUnitId());
            if (!belongsToUnit) {
                throw new RuntimeException("Residents can only open calls in their own units");
            }
        }

        call.setCreatedAt(LocalDateTime.now());
        call.setStatusId(1L);
        Call createdCall = callGateway.createCall(call);

        annexes.stream().forEach(file -> {
            String filePath = fileStorageGateway.store(file);

            Annex annex = new Annex(createdCall.getId(), file.getOriginalFilename(), filePath);
            annexGateway.createAnnex(annex);
        });

        return createdCall;
    }
}
