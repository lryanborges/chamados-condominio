package com.dunnas.chamados_condominio.application.usecases.call;

import com.dunnas.chamados_condominio.application.exceptions.ForbiddenException;
import com.dunnas.chamados_condominio.application.gateways.*;
import com.dunnas.chamados_condominio.domain.entity.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

public class CreateCall {
    private final CallGateway callGateway;
    private final AnnexGateway annexGateway;
    private final FileStorageGateway fileStorageGateway;
    private final UserGateway userGateway;
    private final CallTypeGateway callTypeGateway;

    public CreateCall(CallGateway callGateway, AnnexGateway annexGateway, FileStorageGateway fileStorageGateway, UserGateway userGateway, CallTypeGateway callTypeGateway) {
        this.callGateway = callGateway;
        this.annexGateway = annexGateway;
        this.fileStorageGateway = fileStorageGateway;
        this.userGateway = userGateway;
        this.callTypeGateway = callTypeGateway;
    }

    public Call createCall(Call call, List<MultipartFile> annexes, String loggedUserEmail) {
        User loggedUser = userGateway.findUserByEmail(loggedUserEmail);

        System.out.println("User units: " + loggedUser.getUnitIds());
        System.out.println("Call unit: " + call.getUnitId());

        if (loggedUser.getRole() == Role.RESIDENT) {
            boolean belongsToUnit = loggedUser.getUnitIds().contains(call.getUnitId());
            if (!belongsToUnit) {
                throw new ForbiddenException("Residents can only open calls in their own units");
            }
        }

        CallType callType = callTypeGateway.findCallTypeById(call.getCallTypeId());
        LocalDateTime createdAt = LocalDateTime.now();
        Long hours = callType.getDeadline().longValue();
        Long minutes = Math.round((callType.getDeadline() % 1) * 60);
        LocalDateTime deadline = LocalDateTime.now().plusHours(hours).plusMinutes(minutes);

        call.setUserId(loggedUser.getId());
        call.setStatusId(1L);
        call.setCreatedAt(createdAt);
        call.setDeadline(deadline);

        Call createdCall = callGateway.createCall(call);

        annexes.stream().forEach(file -> {
            String filePath = fileStorageGateway.store(file);

            Annex annex = new Annex(createdCall.getId(), file.getOriginalFilename(), filePath);
            annexGateway.createAnnex(annex);
        });

        return createdCall;
    }
}
