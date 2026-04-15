package com.dunnas.chamados_condominio.infrastructure.persistence.status;

import jakarta.persistence.*;

@Entity
@Table(name = "status")
public class StatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    private Boolean isFinal;

    public StatusEntity() {
    }

    public StatusEntity(String name, Boolean isFinal) {
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

    public Boolean getIsFinal() {
        return isFinal;
    }

    public void setIsFinal(Boolean isFinal) {
        this.isFinal = isFinal;
    }
}
