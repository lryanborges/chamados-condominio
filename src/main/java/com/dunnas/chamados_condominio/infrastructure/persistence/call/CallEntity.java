package com.dunnas.chamados_condominio.infrastructure.persistence.call;

import com.dunnas.chamados_condominio.infrastructure.persistence.annex.AnnexEntity;
import com.dunnas.chamados_condominio.infrastructure.persistence.calltype.CallTypeEntity;
import com.dunnas.chamados_condominio.infrastructure.persistence.status.StatusEntity;
import com.dunnas.chamados_condominio.infrastructure.persistence.unit.UnitEntity;
import com.dunnas.chamados_condominio.infrastructure.persistence.user.UserEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "call")
public class CallEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private LocalDateTime deadline;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id")
    private UnitEntity unit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id")
    private StatusEntity status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "call_type_id")
    private CallTypeEntity callType;

    private LocalDateTime createdAt;
    private LocalDateTime finishedAt;

    public CallEntity() {}

    public CallEntity(String title, String description, LocalDateTime deadline, UserEntity user, UnitEntity unit, StatusEntity status, CallTypeEntity callType) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.user = user;
        this.unit = unit;
        this.status = status;
        this.callType = callType;
    }

    public CallEntity(StatusEntity status){
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(LocalDateTime finishedAt) {
        this.finishedAt = finishedAt;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public UnitEntity getUnit() {
        return unit;
    }

    public void setUnit(UnitEntity unit) {
        this.unit = unit;
    }

    public StatusEntity getStatus() {
        return status;
    }

    public void setStatus(StatusEntity status) {
        this.status = status;
    }

    public CallTypeEntity getCallType() {
        return callType;
    }

    public void setCallType(CallTypeEntity callType) {
        this.callType = callType;
    }
}
