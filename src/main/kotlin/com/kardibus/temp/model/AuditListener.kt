package com.kardibus.temp.model

import jakarta.persistence.PrePersist
import java.time.LocalDateTime

open class AuditListener {

    @PrePersist
    open fun beforeInsert(baseEntity: BaseEntity) {
        baseEntity.createdAt = LocalDateTime.now()
    }
}