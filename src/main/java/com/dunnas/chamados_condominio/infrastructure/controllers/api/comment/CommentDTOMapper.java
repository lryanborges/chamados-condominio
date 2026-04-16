package com.dunnas.chamados_condominio.infrastructure.controllers.api.comment;

import com.dunnas.chamados_condominio.application.gateways.CallGateway;
import com.dunnas.chamados_condominio.domain.entity.Call;
import com.dunnas.chamados_condominio.domain.entity.Comment;
import com.dunnas.chamados_condominio.infrastructure.controllers.api.call.CallDTOMapper;
import com.dunnas.chamados_condominio.infrastructure.controllers.api.call.CallResponse;
import com.dunnas.chamados_condominio.infrastructure.controllers.api.user.UserDTOMapper;
import com.dunnas.chamados_condominio.infrastructure.controllers.api.user.UserResponse;

public class CommentDTOMapper {
    private final CallGateway callGateway;
    private final CallDTOMapper callDTOMapper;
    private final UserDTOMapper userDTOMapper;

    public CommentDTOMapper(CallGateway callGateway, CallDTOMapper callDTOMapper, UserDTOMapper userDTOMapper) {
        this.callGateway = callGateway;
        this.callDTOMapper = callDTOMapper;
        this.userDTOMapper = userDTOMapper;
    }

    public CommentResponse toResponse(Comment comment) {
        CallResponse callResponse = callDTOMapper.toResponse(comment.getCall());
        UserResponse userResponse = userDTOMapper.toResponse(comment.getUser());
        return new CommentResponse(comment.getId(), comment.getContent(), callResponse, userResponse, comment.getCreatedAt());
    }

    public Comment toEntity(CommentRequest request) {
        Call call = callGateway.findCallById(request.callId());
        return new Comment(request.content(), call);
    }
}
