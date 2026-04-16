package com.dunnas.chamados_condominio.main;

import com.dunnas.chamados_condominio.application.gateways.CallGateway;
import com.dunnas.chamados_condominio.application.gateways.CallTypeGateway;
import com.dunnas.chamados_condominio.application.gateways.CommentGateway;
import com.dunnas.chamados_condominio.application.gateways.UserGateway;
import com.dunnas.chamados_condominio.application.usecases.comment.CreateComment;
import com.dunnas.chamados_condominio.application.usecases.comment.FindAllComents;
import com.dunnas.chamados_condominio.application.usecases.comment.FindCommentsByCallId;
import com.dunnas.chamados_condominio.infrastructure.controllers.api.call.CallDTOMapper;
import com.dunnas.chamados_condominio.infrastructure.controllers.api.comment.CommentDTOMapper;
import com.dunnas.chamados_condominio.infrastructure.controllers.api.user.UserDTOMapper;
import com.dunnas.chamados_condominio.infrastructure.gateways.call.CallEntityMapper;
import com.dunnas.chamados_condominio.infrastructure.gateways.comment.CommentEntityMapper;
import com.dunnas.chamados_condominio.infrastructure.gateways.comment.CommentRepositoryGateway;
import com.dunnas.chamados_condominio.infrastructure.gateways.user.UserEntityMapper;
import com.dunnas.chamados_condominio.infrastructure.persistence.comment.CommentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommentConfig {
    @Bean
    CreateComment createComment(CommentGateway commentGateway, UserGateway userGateway) {
        return new CreateComment(commentGateway, userGateway);
    }

    @Bean
    FindAllComents findAllComents(CommentGateway commentGateway) {
        return new FindAllComents(commentGateway);
    }

    @Bean
    FindCommentsByCallId findCommentsByCallId(CommentGateway commentGateway) {
        return new FindCommentsByCallId(commentGateway);
    }

    @Bean
    CommentGateway commentGateway(CommentRepository commentRepository, CommentEntityMapper commentEntityMapper) {
        return new CommentRepositoryGateway(commentRepository, commentEntityMapper);
    }

    @Bean
    CommentEntityMapper commentEntityMapper(UserEntityMapper userEntityMapper, CallEntityMapper callEntityMapper) {
        return new CommentEntityMapper(userEntityMapper, callEntityMapper);
    }

    @Bean
    CommentDTOMapper commentDTOMapper(CallGateway callGateway, CallDTOMapper callDTOMapper, UserDTOMapper userDTOMapper) {
        return new CommentDTOMapper(callGateway, callDTOMapper, userDTOMapper);
    }
}
