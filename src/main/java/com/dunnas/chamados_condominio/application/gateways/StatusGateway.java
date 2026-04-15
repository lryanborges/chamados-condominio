package com.dunnas.chamados_condominio.application.gateways;

import com.dunnas.chamados_condominio.domain.entity.Status;

import java.util.List;

public interface StatusGateway {
    Status createStatus(Status status);
    Status findStatusById(Long id);
    List<Status> findAllStatus();
}
