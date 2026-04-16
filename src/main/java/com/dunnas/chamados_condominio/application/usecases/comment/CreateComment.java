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
    private final UserGateway userGateway;

    public CreateComment(CommentGateway commentGateway, UserGateway userGateway) {
        this.commentGateway = commentGateway;
        this.userGateway = userGateway;
    }

    public Comment createComment(Comment comment, String loggedUserEmail) {
        if (comment == null) {
            throw new BadRequestException("Comment must not be null");
        }
        if (comment.getCall().getId() == null) {
            throw new BadRequestException("Call id must not be null");
        }

        User loggedUser = userGateway.findUserByEmail(loggedUserEmail);

        if (comment.getCall() == null) {
            throw new NotFoundException("Call not found");
        }
        if (loggedUser == null) {
            throw new NotFoundException("User not found");
        }

        CallType calltype = comment.getCall().getCallType();

        if (loggedUser.getRole() == Role.RESIDENT) {
            boolean belongsToUnit = loggedUser.getUnitIds().contains(comment.getCall().getUnit().getId());
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
        comment.setUser(loggedUser);
        return commentGateway.createComment(comment);
    }
}
