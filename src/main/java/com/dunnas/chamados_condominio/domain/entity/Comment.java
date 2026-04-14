package com.dunnas.chamados_condominio.domain.entity;

import java.time.LocalDateTime;

public class Comment {
    private Long id;
    private String content;
    private Long callId;
    private Long userId;
    private LocalDateTime createdAt;

    public Comment(String content, Long callId, Long userId) {
        this.content = content;
        this.callId = callId;
        this.userId = userId;
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

    public Long getCallId() {
        return callId;
    }

    public void setCallId(Long callId) {
        this.callId = callId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
