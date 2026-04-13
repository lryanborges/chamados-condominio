package com.dunnas.chamados_condominio.infrastructure.persistence.annex;

import jakarta.persistence.*;

@Entity
@Table(name = "annex")
public class AnnexEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long callId;
    private String fileName;
    private String filePath;

    public AnnexEntity() {}

    public AnnexEntity(Long callId, String fileName, String filePath) {
        this.callId = callId;
        this.fileName = fileName;
        this.filePath = filePath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCallId() {
        return callId;
    }

    public void setCallId(Long callId) {
        this.callId = callId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
