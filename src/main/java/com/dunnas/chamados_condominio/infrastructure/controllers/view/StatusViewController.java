package com.dunnas.chamados_condominio.infrastructure.controllers.view;

import com.dunnas.chamados_condominio.application.usecases.status.CreateStatus;
import com.dunnas.chamados_condominio.application.usecases.status.FindAllStatus;
import com.dunnas.chamados_condominio.domain.entity.Status;
import com.dunnas.chamados_condominio.infrastructure.controllers.api.status.CreateStatusRequest;
import com.dunnas.chamados_condominio.infrastructure.controllers.api.status.StatusDTOMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/status")
public class StatusViewController {
    private final FindAllStatus findAllStatus;
    private final CreateStatus createStatus;
    private final StatusDTOMapper statusDTOMapper;

    public StatusViewController(FindAllStatus findAllStatus, CreateStatus createStatus, StatusDTOMapper statusDTOMapper) {
        this.findAllStatus = findAllStatus;
        this.createStatus = createStatus;
        this.statusDTOMapper = statusDTOMapper;
    }

    @GetMapping
    public String listStatus(Model model) {
        List<Status> status = findAllStatus.findAllStatus();

        model.addAttribute("status", status);

        return "status/status";
    }

    @PostMapping
    public String createStatus(@ModelAttribute CreateStatusRequest request) {
        Status status = statusDTOMapper.toEntity(request);
        createStatus.createStatus(status);

        return "redirect:/status";
    }
}
