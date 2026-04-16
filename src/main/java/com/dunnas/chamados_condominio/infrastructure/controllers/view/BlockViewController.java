package com.dunnas.chamados_condominio.infrastructure.controllers.view;

import com.dunnas.chamados_condominio.application.usecases.block.CreateBlock;
import com.dunnas.chamados_condominio.application.usecases.block.FindAllBlocks;
import com.dunnas.chamados_condominio.domain.entity.Block;
import com.dunnas.chamados_condominio.infrastructure.controllers.api.block.BlockDTOMapper;
import com.dunnas.chamados_condominio.infrastructure.controllers.api.block.CreateBlockRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("blocks")
public class BlockViewController {
    private final CreateBlock createBlock;
    private final FindAllBlocks findAllBlocks;
    private final BlockDTOMapper blockDTOMapper;

    public BlockViewController(CreateBlock createBlock, FindAllBlocks findAllBlocks, BlockDTOMapper blockDTOMapper) {
        this.createBlock = createBlock;
        this.findAllBlocks = findAllBlocks;
        this.blockDTOMapper = blockDTOMapper;
    }

    @GetMapping
    public String listBlocks(Model model) {
        model.addAttribute("blocks", findAllBlocks.findAllBlocks());
        return "block/blocks";    }

    @GetMapping("/new")
    public String formCreateBlock() {
        return "block/block-form";
    }

    @PostMapping
    public String createBlock(@ModelAttribute CreateBlockRequest request, RedirectAttributes redirectAttributes) {
        try {
            Block block = blockDTOMapper.toEntity(request);
            createBlock.createBlock(block);
            redirectAttributes.addFlashAttribute("successMessage", "Bloco criado com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao criar bloco.");
        }

        return "redirect:/blocks";
    }
}
