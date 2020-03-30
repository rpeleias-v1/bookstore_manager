package com.rodrigopeleias.bookstoremanager.entities;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;

@Data
@Builder
@Embeddable
public class Audit {

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    @PostUpdate
    public void postUpdate() {
        this.updatedAt = LocalDateTime.now();

    }
}
