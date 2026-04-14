package com.dunnas.chamados_condominio.main;

import com.dunnas.chamados_condominio.application.gateways.CallGateway;
import com.dunnas.chamados_condominio.application.gateways.CommentGateway;
import com.dunnas.chamados_condominio.application.gateways.UserGateway;
import com.dunnas.chamados_condominio.application.usecases.comment.CreateComment;
import com.dunnas.chamados_condominio.infrastructure.controllers.comment.CommentDTOMapper;
import com.dunnas.chamados_condominio.infrastructure.gateways.comment.CommentEntityMapper;
import com.dunnas.chamados_condominio.infrastructure.gateways.comment.CommentRepositoryGateway;
import com.dunnas.chamados_condominio.infrastructure.persistence.comment.CommentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommentConfig {
    @Bean
    CreateComment createComment(CommentGateway commentGateway, CallGateway callGateway, UserGateway userGateway) {
        return new CreateComment(commentGateway, callGateway, userGateway);
    }

    @Bean
    CommentGateway commentGateway(CommentRepository commentRepository, CommentEntityMapper commentEntityMapper) {
        return new CommentRepositoryGateway(commentRepository, commentEntityMapper);
    }

    @Bean
    CommentEntityMapper commentEntityMapper() {
        return new CommentEntityMapper();
    }

    @Bean
    CommentDTOMapper commentDTOMapper() {
        return new CommentDTOMapper();
    }
}
