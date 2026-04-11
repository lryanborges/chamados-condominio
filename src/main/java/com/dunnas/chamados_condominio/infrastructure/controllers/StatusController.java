package com.dunnas.chamados_condominio.infrastructure.controllers;

import com.dunnas.chamados_condominio.application.usecases.CreateStatus;
import com.dunnas.chamados_condominio.domain.entity.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("status")
public class StatusController {
    private final CreateStatus createStatus;
    private final StatusDTOMapper statusDTOMapper;

    public StatusController(final CreateStatus createStatus, StatusDTOMapper statusDTOMapper) {
        this.createStatus = createStatus;
        this.statusDTOMapper = statusDTOMapper;
    }

    @PostMapping
    public CreateStatusResponse createStatus(@RequestBody CreateStatusRequest request) {
        Status newStatus = statusDTOMapper.toEntity(request);
        Status created = createStatus.createStatus(newStatus);
        return statusDTOMapper.toResponse(created);
    }
}
