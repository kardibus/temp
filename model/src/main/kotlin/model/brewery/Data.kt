package model.brewery

import model.AuditListener
import model.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.Table

/**
 * Данные от кипятильника
 */
@Entity
@EntityListeners(AuditListener::class)
@Table(name = "data")
class Data : BaseEntity() {
    /** Температура с датчика */
    @Column(name = "temp")
    var temp: Double = 0.0
}
