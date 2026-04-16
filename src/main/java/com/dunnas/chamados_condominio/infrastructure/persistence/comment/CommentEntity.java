package com.dunnas.chamados_condominio.infrastructure.persistence.comment;

import com.dunnas.chamados_condominio.infrastructure.persistence.call.CallEntity;
import com.dunnas.chamados_condominio.infrastructure.persistence.user.UserEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "call_id", nullable = false)
    private CallEntity call;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
    private LocalDateTime createdAt;

    public CommentEntity() {}

    public CommentEntity(String content, CallEntity call) {
        this.content = content;
        this.call = call;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public CallEntity getCall() {
        return call;
    }

    public void setCall(CallEntity call) {
        this.call = call;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
