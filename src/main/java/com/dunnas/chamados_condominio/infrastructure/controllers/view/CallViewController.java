package com.dunnas.chamados_condominio.infrastructure.controllers.view;

import com.dunnas.chamados_condominio.application.usecases.annex.FindAnnexesByCallId;
import com.dunnas.chamados_condominio.application.usecases.block.FindBlockById;
import com.dunnas.chamados_condominio.application.usecases.call.CreateCall;
import com.dunnas.chamados_condominio.application.usecases.call.FindAllCallByFilters;
import com.dunnas.chamados_condominio.application.usecases.call.FindCallById;
import com.dunnas.chamados_condominio.application.usecases.calltype.FindAllCallTypes;
import com.dunnas.chamados_condominio.application.usecases.calltype.FindCallTypeById;
import com.dunnas.chamados_condominio.application.usecases.comment.CreateComment;
import com.dunnas.chamados_condominio.application.usecases.comment.FindAllComents;
import com.dunnas.chamados_condominio.application.usecases.status.FindStatusById;
import com.dunnas.chamados_condominio.application.usecases.unit.FindUnitById;
import com.dunnas.chamados_condominio.application.usecases.unit.FindUnitsByUserId;
import com.dunnas.chamados_condominio.application.usecases.user.FindUserByEmail;
import com.dunnas.chamados_condominio.application.usecases.user.FindUserById;
import com.dunnas.chamados_condominio.domain.entity.*;
import com.dunnas.chamados_condominio.infrastructure.controllers.api.call.CallDTOMapper;
import com.dunnas.chamados_condominio.infrastructure.controllers.api.call.CallRequest;
import com.dunnas.chamados_condominio.infrastructure.controllers.api.comment.CommentDTOMapper;
import com.dunnas.chamados_condominio.infrastructure.controllers.api.comment.CommentRequest;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.file.Path;
import java.nio.file.Paths;
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
    private final FindUnitsByUserId findUnitsByUserId;
    private final CallDTOMapper callDTOMapper;
    private final CreateCall createCall;
    private final FindAllComents findAllComents;
    private final CreateComment createComment;
    private final CommentDTOMapper commentDTOMapper;
    private final FindAnnexesByCallId findAnnexesByCallId;

    public CallViewController(FindAllCallByFilters findAllCallByFilters, FindUserByEmail findUserByEmail, FindCallById findCallById, FindStatusById findStatusById, FindUserById findUserById, FindUnitById findUnitById, FindBlockById findBlockById, FindCallTypeById findCallTypeById, FindAllCallTypes findAllCallTypes, FindUnitsByUserId findUnitsByUserId, CallDTOMapper callDTOMapper, CreateCall createCall, FindAllComents findAllComents, CreateComment createComment, CommentDTOMapper commentDTOMapper, FindAnnexesByCallId findAnnexesByCallId) {
        this.findAllCallByFilters = findAllCallByFilters;
        this.findUserByEmail = findUserByEmail;
        this.findCallById = findCallById;
        this.findStatusById = findStatusById;
        this.findUserById = findUserById;
        this.findUnitById = findUnitById;
        this.findBlockById = findBlockById;
        this.findCallTypeById = findCallTypeById;
        this.findAllCallTypes = findAllCallTypes;
        this.findUnitsByUserId = findUnitsByUserId;
        this.callDTOMapper = callDTOMapper;
        this.createCall = createCall;
        this.findAllComents = findAllComents;
        this.createComment = createComment;
        this.commentDTOMapper = commentDTOMapper;
        this.findAnnexesByCallId = findAnnexesByCallId;
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
        List<Comment> comments = findAllComents.findAllComments();
        List<Annex> annexes = findAnnexesByCallId.findAnnexByCallId(call.getId());

        model.addAttribute("call", call);
        model.addAttribute("status", status);
        model.addAttribute("user", user);
        model.addAttribute("unit", unit);
        model.addAttribute("block", block);
        model.addAttribute("callType", callType);
        model.addAttribute("comments", comments);
        model.addAttribute("annexes", annexes);
        return "call/call-detail";
    }

    @PostMapping
    public String createCall(@ModelAttribute CallRequest request,
                             @RequestParam(value = "annexes", required = false) List<MultipartFile> files) {
        String loggedUserEmail = SecurityContextHolder.getContext()
                .getAuthentication().getName();

        Call call = callDTOMapper.toEntity(request);
        createCall.createCall(call, files, loggedUserEmail);

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

}
