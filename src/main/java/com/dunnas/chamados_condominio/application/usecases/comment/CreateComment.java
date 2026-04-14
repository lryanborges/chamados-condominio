package com.dunnas.chamados_condominio.application.usecases.comment;

import com.dunnas.chamados_condominio.application.gateways.CallGateway;
import com.dunnas.chamados_condominio.application.gateways.CommentGateway;
import com.dunnas.chamados_condominio.application.gateways.UserGateway;
import com.dunnas.chamados_condominio.domain.entity.Call;
import com.dunnas.chamados_condominio.domain.entity.Comment;
import com.dunnas.chamados_condominio.domain.entity.Role;
import com.dunnas.chamados_condominio.domain.entity.User;

import java.time.LocalDateTime;

public class CreateComment {
    private final CommentGateway commentGateway;
    private final CallGateway callGateway;
    private final UserGateway userGateway;

    public CreateComment(CommentGateway commentGateway, CallGateway callGateway, UserGateway userGateway) {
        this.commentGateway = commentGateway;
        this.callGateway = callGateway;
        this.userGateway = userGateway;
    }

    public Comment createComment(Comment comment, String loggedUserEmail) {
        Call call = callGateway.findCallById(comment.getCallId());
        User loggedUser = userGateway.findUserByEmail(loggedUserEmail);


        if (call == null) {
            throw new RuntimeException("Call not found");
        }
        if (loggedUser == null) {
            throw new RuntimeException("User not found");
        }

        if (loggedUser.getRole() == Role.RESIDENT) {
            boolean belongsToUnit = loggedUser.getUnitIds().contains(call.getUnitId());
            if (!belongsToUnit) {
                throw new RuntimeException("Residents can only comment in their own units");
            }
        }

        if (loggedUser.getRole() == Role.COLLABORATOR) {

        }

        comment.setCreatedAt(LocalDateTime.now());
        return commentGateway.createComment(comment);
    }
}
