package com.kardibus.temp.model

import jakarta.persistence.PrePersist
import java.time.LocalDateTime

class AuditListener {
    @PrePersist
    fun beforeInsert(baseEntity: BaseEntity) {
        baseEntity.createdAt = LocalDateTime.now()
    }
}
