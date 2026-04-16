package com.dunnas.chamados_condominio.domain.entity;

import java.time.LocalDateTime;

public class Comment {
    private Long id;
    private String content;
    private Call call;
    private User user;
    private LocalDateTime createdAt;

    public Comment(String content, Call call) {
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

    public Call getCall() {
        return call;
    }

    public void setCall(Call call) {
        this.call = call;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
