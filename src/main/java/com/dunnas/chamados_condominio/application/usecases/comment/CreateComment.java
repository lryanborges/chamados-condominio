package com.dunnas.chamados_condominio.application.usecases.comment;

import com.dunnas.chamados_condominio.application.exceptions.BadRequestException;
import com.dunnas.chamados_condominio.application.exceptions.ForbiddenException;
import com.dunnas.chamados_condominio.application.exceptions.NotFoundException;
import com.dunnas.chamados_condominio.application.gateways.CallGateway;
import com.dunnas.chamados_condominio.application.gateways.CallTypeGateway;
import com.dunnas.chamados_condominio.application.gateways.CommentGateway;
import com.dunnas.chamados_condominio.application.gateways.UserGateway;
import com.dunnas.chamados_condominio.domain.entity.*;

import java.time.LocalDateTime;

public class CreateComment {
    private final CommentGateway commentGateway;
    private final CallGateway callGateway;
    private final UserGateway userGateway;
    private final CallTypeGateway callTypeGateway;

    public CreateComment(CommentGateway commentGateway, CallGateway callGateway, UserGateway userGateway, CallTypeGateway callTypeGateway) {
        this.commentGateway = commentGateway;
        this.callGateway = callGateway;
        this.userGateway = userGateway;
        this.callTypeGateway = callTypeGateway;
    }

    public Comment createComment(Comment comment, String loggedUserEmail) {
        if (comment == null) {
            throw new BadRequestException("Comment must not be null");
        }
        if (comment.getCallId() == null) {
            throw new BadRequestException("Call id must not be null");
        }


        Call call = callGateway.findCallById(comment.getCallId());
        User loggedUser = userGateway.findUserByEmail(loggedUserEmail);

        if (call == null) {
            throw new NotFoundException("Call not found");
        }
        if (loggedUser == null) {
            throw new NotFoundException("User not found");
        }

        CallType calltype = callTypeGateway.findCallTypeById(call.getCallTypeId());

        if (loggedUser.getRole() == Role.RESIDENT) {
            boolean belongsToUnit = loggedUser.getUnitIds().contains(call.getUnitId());
            if (!belongsToUnit) {
                throw new ForbiddenException("Residents can only comment in their own units");
            }
        }

        if (loggedUser.getRole() == Role.COLLABORATOR) {
            boolean belongsToScope = loggedUser.getScope().equalsIgnoreCase(calltype.getTitle());
            if(!belongsToScope) {
                throw new ForbiddenException("Collaborators can only comment in their scope");
            }
        }

        comment.setCreatedAt(LocalDateTime.now());
        comment.setUserId(loggedUser.getId());
        return commentGateway.createComment(comment);
    }
}
