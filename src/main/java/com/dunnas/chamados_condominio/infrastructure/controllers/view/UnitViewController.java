package com.dunnas.chamados_condominio.infrastructure.controllers.view;

import com.dunnas.chamados_condominio.application.usecases.block.FindBlockById;
import com.dunnas.chamados_condominio.application.usecases.unit.FindUnitsByBlockId;
import com.dunnas.chamados_condominio.application.usecases.user.FindAllUsers;
import com.dunnas.chamados_condominio.application.usecases.user.LinkUserToUnit;
import com.dunnas.chamados_condominio.domain.entity.Block;
import com.dunnas.chamados_condominio.domain.entity.Unit;
import com.dunnas.chamados_condominio.domain.entity.User;
import com.dunnas.chamados_condominio.infrastructure.gateways.unit.UnitEntityMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("units")
public class UnitViewController {
    private final FindUnitsByBlockId findUnitsByBlockId;
    private final FindBlockById findBlockById;
    private final FindAllUsers findAllUsers;
    private final LinkUserToUnit linkUserToUnit;
    private final UnitEntityMapper unitEntityMapper;

    public UnitViewController(FindUnitsByBlockId findUnitsByBlockId, FindBlockById findBlockById, FindAllUsers findAllUsers, LinkUserToUnit linkUserToUnit, UnitEntityMapper unitEntityMapper) {
        this.findUnitsByBlockId = findUnitsByBlockId;
        this.findBlockById = findBlockById;
        this.findAllUsers = findAllUsers;
        this.linkUserToUnit = linkUserToUnit;
        this.unitEntityMapper = unitEntityMapper;
    }

    @GetMapping("/{blockId}")
    public String listUnits(@PathVariable("blockId") Long blockId, Model model) {
        List<Unit> units = findUnitsByBlockId.findUnitsByBlockId(blockId);
        List<User> allUsers = findAllUsers.findAllUsers();
        Block block = findBlockById.findBlockById(blockId);

        model.addAttribute("units", units);
        model.addAttribute("allUsers", allUsers);
        model.addAttribute("block", block);
        return "unit/units";
    }

    @PostMapping("/link-user")
    public String linkUserToUnit(@RequestParam("userId") Long userId,
                                 @RequestParam("unitId") Long unitId,
                                 @RequestParam("blockId") Long blockId,
                                 RedirectAttributes redirectAttributes) {
        String loggedUserEmail = SecurityContextHolder.getContext()
                .getAuthentication().getName();

        try {
            List<Long> units = new ArrayList<>();
            units.add(unitId);
            linkUserToUnit.link(userId, units, loggedUserEmail);
            redirectAttributes.addFlashAttribute("successMessage", "Morador vinculado com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Este morador já está vinculado a esta unidade.");
        }

        return "redirect:/units/" + blockId;
    }
}
