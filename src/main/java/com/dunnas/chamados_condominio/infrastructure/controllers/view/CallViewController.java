package com.dunnas.chamados_condominio.infrastructure.controllers.view;

import com.dunnas.chamados_condominio.application.gateways.CallGateway;
import com.dunnas.chamados_condominio.application.usecases.block.FindBlockById;
import com.dunnas.chamados_condominio.application.usecases.call.FindAllCallByFilters;
import com.dunnas.chamados_condominio.application.usecases.call.FindCallById;
import com.dunnas.chamados_condominio.application.usecases.calltype.FindAllCallTypes;
import com.dunnas.chamados_condominio.application.usecases.calltype.FindCallTypeById;
import com.dunnas.chamados_condominio.application.usecases.status.FindAllStatus;
import com.dunnas.chamados_condominio.application.usecases.status.FindStatusById;
import com.dunnas.chamados_condominio.application.usecases.unit.FindUnitById;
import com.dunnas.chamados_condominio.application.usecases.user.FindUserByEmail;
import com.dunnas.chamados_condominio.application.usecases.user.FindUserById;
import com.dunnas.chamados_condominio.domain.entity.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("calls")
public class CallViewController {
    private final FindAllCallByFilters findAllCallByFilters;
    private final FindUserByEmail findUserByEmail;
    private final FindCallById findCallById;
    private final FindStatusById findStatusById;
    private final FindUserById findUserById;
    private final FindUnitById findUnitById;
    private final FindBlockById findBlockById;
    private final FindCallTypeById findCallTypeById;
    private final FindAllCallTypes findAllCallTypes;

    public CallViewController(FindAllCallByFilters findAllCallByFilters, FindUserByEmail findUserByEmail, FindCallById findCallById, FindStatusById findStatusById, FindUserById findUserById, FindUnitById findUnitById, FindBlockById findBlockById, FindCallTypeById findCallTypeById, FindAllCallTypes findAllCallTypes) {
        this.findAllCallByFilters = findAllCallByFilters;
        this.findUserByEmail = findUserByEmail;
        this.findCallById = findCallById;
        this.findStatusById = findStatusById;
        this.findUserById = findUserById;
        this.findUnitById = findUnitById;
        this.findBlockById = findBlockById;
        this.findCallTypeById = findCallTypeById;
        this.findAllCallTypes = findAllCallTypes;
    }

    @GetMapping("/new")
    public String formCreateCall(Model model) {
        List<CallType> callTypes = findAllCallTypes.findAllCallTypes();

        model.addAttribute("callTypes", callTypes);

        return "call/call-form";
    }

    @GetMapping
    public String listCalls(@RequestParam(required = false) Long statusId, Model model) {
        String loggedUserEmail = SecurityContextHolder.getContext()
                .getAuthentication().getName();
        User loggedUser = findUserByEmail.findUserByEmail(loggedUserEmail);
        List<Call> calls = findAllCallByFilters.findAllCallByFilters(loggedUserEmail, statusId);

        model.addAttribute("calls", calls);
        model.addAttribute("loggedUser", loggedUser);

        return "call/calls";
    }

    @GetMapping("/{id}")
    public String getCall(@PathVariable Long id, Model model) {
        Call call = findCallById.findCallById(id);
        Status status = findStatusById.findStatusById(call.getStatusId());
        User user = findUserById.findUserById(call.getUserId());
        Unit unit = findUnitById.findUnitById(call.getUnitId());
        Block block = findBlockById.findBlockById(unit.getBlockId());
        CallType callType = findCallTypeById.findCallTypeById(call.getCallTypeId());

        model.addAttribute("call", call);
        model.addAttribute("status", status);
        model.addAttribute("user", user);
        model.addAttribute("unit", unit);
        model.addAttribute("block", block);
        model.addAttribute("callType", callType);
        return "call/call-detail";
    }

}
