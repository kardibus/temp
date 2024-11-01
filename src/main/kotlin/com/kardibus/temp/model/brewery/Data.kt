package com.kardibus.temp.model.brewery

import com.kardibus.temp.model.AudiListener
import com.kardibus.temp.model.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.util.UUID
import jdk.jfr.Label
import org.hibernate.annotations.UuidGenerator

/**
 * Данные от кипятильника
 */
@Entity
@EntityListeners(AudiListener::class)
@Table(name = "data")
open class Data: BaseEntity() {

    /** Температура с датчика */
    @Column(name = "temp")
    open var temp: Double = 0.0
}