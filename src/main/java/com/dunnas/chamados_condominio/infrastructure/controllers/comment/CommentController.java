package com.dunnas.chamados_condominio.infrastructure.controllers.comment;

import com.dunnas.chamados_condominio.application.usecases.comment.CreateComment;
import com.dunnas.chamados_condominio.domain.entity.Comment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("comments")
public class CommentController {
    private final CreateComment createComment;
    private final CommentDTOMapper commentDTOMapper;

    public CommentController(CreateComment createComment, CommentDTOMapper commentDTOMapper) {
        this.createComment = createComment;
        this.commentDTOMapper = commentDTOMapper;
    }

    @PostMapping
    public CommentResponse createComment(@RequestBody CommentRequest request) {
        String loggedUserEmail = SecurityContextHolder.getContext()
                .getAuthentication().getName();
        Comment comment = commentDTOMapper.toEntity(request);
        Comment createdComment = createComment.createComment(comment, loggedUserEmail);
        return commentDTOMapper.toResponse(createdComment);
    }
}
