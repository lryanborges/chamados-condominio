package com.dunnas.chamados_condominio.infrastructure.controllers.view;

import com.dunnas.chamados_condominio.application.exceptions.BadRequestException;
import com.dunnas.chamados_condominio.application.usecases.annex.FindAnnexById;
import com.dunnas.chamados_condominio.application.usecases.annex.FindAnnexesByCallId;
import com.dunnas.chamados_condominio.application.usecases.call.CreateCall;
import com.dunnas.chamados_condominio.application.usecases.call.FindAllCallByFilters;
import com.dunnas.chamados_condominio.application.usecases.call.FindCallById;
import com.dunnas.chamados_condominio.application.usecases.call.UpdateCall;
import com.dunnas.chamados_condominio.application.usecases.calltype.FindAllCallTypes;
import com.dunnas.chamados_condominio.application.usecases.comment.CreateComment;
import com.dunnas.chamados_condominio.application.usecases.comment.FindCommentsByCallId;
import com.dunnas.chamados_condominio.application.usecases.status.FindAllStatus;
import com.dunnas.chamados_condominio.application.usecases.unit.FindUnitsByUserId;
import com.dunnas.chamados_condominio.application.usecases.user.FindUserByEmail;
import com.dunnas.chamados_condominio.domain.entity.*;
import com.dunnas.chamados_condominio.infrastructure.controllers.api.call.CallDTOMapper;
import com.dunnas.chamados_condominio.infrastructure.controllers.api.call.CallRequest;
import com.dunnas.chamados_condominio.infrastructure.controllers.api.call.UpdateCallRequest;
import com.dunnas.chamados_condominio.infrastructure.controllers.api.comment.CommentDTOMapper;
import com.dunnas.chamados_condominio.infrastructure.controllers.api.comment.CommentRequest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

@Controller
@RequestMapping("calls")
public class CallViewController {
    private final FindAllCallByFilters findAllCallByFilters;
    private final FindUserByEmail findUserByEmail;
    private final FindCallById findCallById;
    private final FindAllCallTypes findAllCallTypes;
    private final FindUnitsByUserId findUnitsByUserId;
    private final CallDTOMapper callDTOMapper;
    private final CreateCall createCall;
    private final FindCommentsByCallId findCommentsByCallId;
    private final CreateComment createComment;
    private final CommentDTOMapper commentDTOMapper;
    private final FindAnnexesByCallId findAnnexesByCallId;
    private final FindAllStatus findAllStatus;
    private final UpdateCall updateCall;
    private final FindAnnexById findAnnexById;

    public CallViewController(FindAllCallByFilters findAllCallByFilters, FindUserByEmail findUserByEmail, FindCallById findCallById, FindAllCallTypes findAllCallTypes, FindUnitsByUserId findUnitsByUserId, CallDTOMapper callDTOMapper, CreateCall createCall, FindCommentsByCallId findCommentsByCallId, CreateComment createComment, CommentDTOMapper commentDTOMapper, FindAnnexesByCallId findAnnexesByCallId, FindAllStatus findAllStatus, UpdateCall updateCall, FindAnnexById findAnnexById) {
        this.findAllCallByFilters = findAllCallByFilters;
        this.findUserByEmail = findUserByEmail;
        this.findCallById = findCallById;
        this.findAllCallTypes = findAllCallTypes;
        this.findUnitsByUserId = findUnitsByUserId;
        this.callDTOMapper = callDTOMapper;
        this.createCall = createCall;
        this.findCommentsByCallId = findCommentsByCallId;
        this.createComment = createComment;
        this.commentDTOMapper = commentDTOMapper;
        this.findAnnexesByCallId = findAnnexesByCallId;
        this.findAllStatus = findAllStatus;
        this.updateCall = updateCall;
        this.findAnnexById = findAnnexById;
    }

    @GetMapping("/new")
    public String formCreateCall(Model model) {
        String loggedUserEmail = SecurityContextHolder.getContext()
                .getAuthentication().getName();
        User loggedUser = findUserByEmail.findUserByEmail(loggedUserEmail);

        List<CallType> callTypes = findAllCallTypes.findAllCallTypes();
        List<Unit> userUnits = findUnitsByUserId.findUnitsByUserId(loggedUser.getId());

        model.addAttribute("callTypes", callTypes);
        model.addAttribute("loggedUser", loggedUser);
        model.addAttribute("userUnits", userUnits);

        return "call/call-form";
    }

    @GetMapping
    public String listCalls(@RequestParam(required = false) Long statusId, Model model) {
        String loggedUserEmail = SecurityContextHolder.getContext()
                .getAuthentication().getName();
        User loggedUser = findUserByEmail.findUserByEmail(loggedUserEmail);
        List<Call> calls = findAllCallByFilters.findAllCallByFilters(loggedUserEmail, statusId);
        List<Status> status = findAllStatus.findAllStatus();

        model.addAttribute("calls", calls);
        model.addAttribute("loggedUser", loggedUser);
        model.addAttribute("status", status);

        return "call/calls";
    }

    @GetMapping("/{id}")
    public String getCall(@PathVariable Long id, Model model) {
        Call call = findCallById.findCallById(id);
        Block block = call.getUnit().getBlock();
        List<Comment> comments = findCommentsByCallId.findCommentsByCallId(call.getId());
        List<Annex> annexes = findAnnexesByCallId.findAnnexByCallId(call.getId());

        model.addAttribute("call", call);
        model.addAttribute("block", block);
        model.addAttribute("comments", comments);
        model.addAttribute("annexes", annexes);
        return "call/call-detail";
    }

    @PostMapping
    public String createCall(@ModelAttribute CallRequest request,
                             @RequestParam(value = "annexes", required = false) List<MultipartFile> files,
                             RedirectAttributes redirectAttributes) {
        String loggedUserEmail = SecurityContextHolder.getContext()
                .getAuthentication().getName();

        try {
            Call call = callDTOMapper.toEntity(request);
            createCall.createCall(call, files, loggedUserEmail);
            redirectAttributes.addFlashAttribute("successMessage", "Chamado criado com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao criar chamado.");
        }

        return "redirect:/calls";
    }

    @PostMapping("{callId}/comments")
    public String createComment(@PathVariable("callId") Long callId,
                                @ModelAttribute CommentRequest request,
                                RedirectAttributes redirectAttributes) {
        String loggedUserEmail = SecurityContextHolder.getContext()
                .getAuthentication().getName();
        try {
            Comment comment = commentDTOMapper.toEntity(request);
            createComment.createComment(comment, loggedUserEmail);

            redirectAttributes.addFlashAttribute("successMessage", "Comentário enviado com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao enviar comentário.");
        }

        return "redirect:/calls/" + callId;
    }

    @PostMapping("{callId}/status")
    @PreAuthorize("hasAnyRole('ADMIN', 'COLLABORATOR')")
    public String editStatus(@PathVariable("callId") Long callId, @ModelAttribute UpdateCallRequest request,
                             RedirectAttributes attributes) {
        String loggedUserEmail = SecurityContextHolder.getContext()
                .getAuthentication().getName();

        try {
            Call call = callDTOMapper.toEntityUpdate(request);
            updateCall.updateCall(callId, call, loggedUserEmail);
        } catch (BadRequestException e) {
            attributes.addFlashAttribute("errorMessage", "Não é possível atualizar um status já finalizado.");
        } catch (Exception e) {
            attributes.addFlashAttribute("errorMessage", "Erro inesperado ao atualizar status.");
        }

        return "redirect:/calls";
    }

    @GetMapping("/download/{annexId}")
    public ResponseEntity<Resource> download(@PathVariable Long annexId) {
        Annex annex = findAnnexById.findAnnexById(annexId);

        try {
            File file = new File(annex.getFilePath());
            Resource resource = new FileSystemResource(file);

            if (!resource.exists() || !resource.isReadable()) {
                System.err.println("Arquivo não encontrado no caminho: " + annex.getFilePath());
                return ResponseEntity.notFound().build();
            }

            String contentType = Files.probeContentType(file.toPath());
            if (contentType == null) {
                contentType = "application/octet-stream";
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + annex.getFileName() + "\"")
                    .body(resource);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

}
