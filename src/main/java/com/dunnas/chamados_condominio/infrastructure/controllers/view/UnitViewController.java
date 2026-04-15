package com.dunnas.chamados_condominio.infrastructure.controllers.view;

import com.dunnas.chamados_condominio.application.usecases.unit.FindUnitsByBlockId;
import com.dunnas.chamados_condominio.domain.entity.Unit;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("units")
public class UnitViewController {
    private final FindUnitsByBlockId findUnitsByBlockId;

    public UnitViewController(FindUnitsByBlockId findUnitsByBlockId) {
        this.findUnitsByBlockId = findUnitsByBlockId;
    }

    @GetMapping("/{blockId}")
    public String listUnits(@PathVariable("blockId") Long blockId, Model model) {
        List<Unit> units = findUnitsByBlockId.findUnitsByBlockId(blockId);
        model.addAttribute("units", units);
        model.addAttribute("blockId", blockId);
        return "unit/units";
    }
}
