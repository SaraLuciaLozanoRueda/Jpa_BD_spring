package com.primer.ejercicio.persistence.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Embeddable
public class Audit {
    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Column(name = "updated_at")
    LocalDateTime updatedAt;

    @PrePersist
    public void prePersistAudit(){
        createAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdateAudit(){
        updatedAt = LocalDateTime.now();
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    
}
