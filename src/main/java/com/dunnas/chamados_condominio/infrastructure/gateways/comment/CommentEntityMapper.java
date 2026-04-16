package com.dunnas.chamados_condominio.infrastructure.gateways.comment;

import com.dunnas.chamados_condominio.domain.entity.Call;
import com.dunnas.chamados_condominio.domain.entity.Comment;
import com.dunnas.chamados_condominio.domain.entity.User;
import com.dunnas.chamados_condominio.infrastructure.gateways.call.CallEntityMapper;
import com.dunnas.chamados_condominio.infrastructure.gateways.user.UserEntityMapper;
import com.dunnas.chamados_condominio.infrastructure.persistence.call.CallEntity;
import com.dunnas.chamados_condominio.infrastructure.persistence.comment.CommentEntity;
import com.dunnas.chamados_condominio.infrastructure.persistence.user.UserEntity;

import java.util.List;

public class CommentEntityMapper {
    private final UserEntityMapper userEntityMapper;
    private final CallEntityMapper callEntityMapper;

    public CommentEntityMapper(UserEntityMapper userEntityMapper, CallEntityMapper callEntityMapper) {
        this.userEntityMapper = userEntityMapper;
        this.callEntityMapper = callEntityMapper;
    }

    public CommentEntity toEntity(Comment comment) {
        UserEntity userEntity = userEntityMapper.toEntity(comment.getUser());
        CallEntity callEntity = callEntityMapper.toEntity(comment.getCall());

        CommentEntity commentEntity = new CommentEntity(comment.getContent(), callEntity);
        commentEntity.setUser(userEntity);
        commentEntity.setId(comment.getId());
        commentEntity.setCreatedAt(comment.getCreatedAt());
        return commentEntity;
    }

    public Comment toDomainObj(CommentEntity commentEntity) {
        User user = userEntityMapper.toDomainObj(commentEntity.getUser());
        Call call = callEntityMapper.toDomainObj(commentEntity.getCall());

        Comment commentDomainObj = new Comment(commentEntity.getContent(), call);
        commentDomainObj.setUser(user);
        commentDomainObj.setId(commentEntity.getId());
        commentDomainObj.setCreatedAt(commentEntity.getCreatedAt());
        return commentDomainObj;
    }

    public List<Comment> toDomainObjList(List<CommentEntity> commentEntityList) {
        return commentEntityList.stream()
                .map(this::toDomainObj)
                .toList();
    }

    public List<CommentEntity> toEntityList(List<Comment> commentList) {
        return commentList.stream()
                .map(this::toEntity)
                .toList();
    }
}
