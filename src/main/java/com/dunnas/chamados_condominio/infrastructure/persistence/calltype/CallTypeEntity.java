package com.dunnas.chamados_condominio.infrastructure.persistence.calltype;

import jakarta.persistence.*;

@Entity
@Table(name = "call_type")
public class CallTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Double deadline;

    public CallTypeEntity() {}

    public CallTypeEntity(String title, Double deadline) {
        this.title = title;
        this.deadline = deadline;
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

    public Double getDeadline() {
        return deadline;
    }

    public void setDeadline(Double deadline) {
        this.deadline = deadline;
    }
}
