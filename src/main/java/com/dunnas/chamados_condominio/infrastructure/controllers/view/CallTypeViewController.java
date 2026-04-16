package com.dunnas.chamados_condominio.infrastructure.controllers.view;

import com.dunnas.chamados_condominio.application.usecases.calltype.CreateCallType;
import com.dunnas.chamados_condominio.application.usecases.calltype.FindAllCallTypes;
import com.dunnas.chamados_condominio.domain.entity.CallType;
import com.dunnas.chamados_condominio.infrastructure.controllers.api.calltype.CallTypeDTOMapper;
import com.dunnas.chamados_condominio.infrastructure.controllers.api.calltype.CreateCallTypeRequest;
import com.dunnas.chamados_condominio.infrastructure.persistence.calltype.CallTypeEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("calltypes")
public class CallTypeViewController {
    FindAllCallTypes findAllCallTypes;
    CreateCallType createCallType;
    CallTypeDTOMapper callTypeDTOMapper;

    public CallTypeViewController(FindAllCallTypes findAllCallTypes, CreateCallType createCallType, CallTypeDTOMapper callTypeDTOMapper) {
        this.findAllCallTypes = findAllCallTypes;
        this.createCallType = createCallType;
        this.callTypeDTOMapper = callTypeDTOMapper;
    }

    @GetMapping
    public String listCallTypes(Model model) {
        List<CallType> calltypes = findAllCallTypes.findAllCallTypes();

        model.addAttribute("callTypes", calltypes);

        return "calltype/calltypes";
    }

    @PostMapping
    public String craateCallType(CreateCallTypeRequest request, RedirectAttributes redirectAttributes) {
        try {
            CallType callType = callTypeDTOMapper.toEntity(request);
            createCallType.createCallType(callType);
            redirectAttributes.addFlashAttribute("successMessage", "Tipo de chamado criado com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao criar tipo de chamado.");
        }

        return "redirect:calltypes";
    }
}
