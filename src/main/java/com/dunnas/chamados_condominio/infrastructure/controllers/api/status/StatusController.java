package com.dunnas.chamados_condominio.infrastructure.controllers.api.status;

import com.dunnas.chamados_condominio.application.usecases.status.CreateStatus;
import com.dunnas.chamados_condominio.application.usecases.status.FindStatusById;
import com.dunnas.chamados_condominio.domain.entity.Status;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/status")
public class StatusController {
    private final CreateStatus createStatus;
    private final FindStatusById findStatusById;
    private final StatusDTOMapper statusDTOMapper;

    public StatusController(final CreateStatus createStatus, FindStatusById findStatusById, StatusDTOMapper statusDTOMapper) {
        this.createStatus = createStatus;
        this.findStatusById = findStatusById;
        this.statusDTOMapper = statusDTOMapper;
    }

    @PostMapping
    public CreateStatusResponse createStatus(@RequestBody CreateStatusRequest request) {
        Status newStatus = statusDTOMapper.toEntity(request);
        Status created = createStatus.createStatus(newStatus);
        return statusDTOMapper.toResponse(created);
    }

    @GetMapping("/{id}")
    public CreateStatusResponse findStatusById(@PathVariable Long id) {
        Status foundedStatus = findStatusById.findStatusById(id);
        return statusDTOMapper.toResponse(foundedStatus);
    }
}
