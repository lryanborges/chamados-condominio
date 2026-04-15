package com.dunnas.chamados_condominio.infrastructure.controllers.view;

import com.dunnas.chamados_condominio.application.usecases.block.FindBlockById;
import com.dunnas.chamados_condominio.application.usecases.unit.FindUnitsByBlockId;
import com.dunnas.chamados_condominio.domain.entity.Block;
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
    private final FindBlockById findBlockById;

    public UnitViewController(FindUnitsByBlockId findUnitsByBlockId, FindBlockById findBlockById) {
        this.findUnitsByBlockId = findUnitsByBlockId;
        this.findBlockById = findBlockById;
    }

    @GetMapping("/{blockId}")
    public String listUnits(@PathVariable("blockId") Long blockId, Model model) {
        List<Unit> units = findUnitsByBlockId.findUnitsByBlockId(blockId);

        Block block = findBlockById.findBlockById(blockId);

        model.addAttribute("units", units);
        model.addAttribute("block", block);
        return "unit/units";
    }
}
