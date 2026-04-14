package com.dunnas.chamados_condominio.domain.entity;

public class Status {
    private Long id;
    private String name;
    private boolean isFinal;

    public Status(String name, boolean isFinal) {
        this.name = name;
        this.isFinal = isFinal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean aFinal) {
        isFinal = aFinal;
    }
}
