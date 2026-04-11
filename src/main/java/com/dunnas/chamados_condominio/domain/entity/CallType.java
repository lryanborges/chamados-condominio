package com.dunnas.chamados_condominio.domain.entity;

public class CallType {
    private Long id;
    private String title;
    private Double deadline;

    public CallType(String title, Double deadline) {
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
