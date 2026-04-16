package com.dunnas.chamados_condominio.infrastructure.persistence.annex;

import com.dunnas.chamados_condominio.infrastructure.persistence.call.CallEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "annex")
public class AnnexEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "call_id", nullable = false)
    private CallEntity call;
    private String fileName;
    private String filePath;

    public AnnexEntity() {}

    public AnnexEntity(CallEntity call, String fileName, String filePath) {
        this.call = call;
        this.fileName = fileName;
        this.filePath = filePath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public CallEntity getCall() {
        return call;
    }

    public void setCall(CallEntity call) {
        this.call = call;
    }
}
