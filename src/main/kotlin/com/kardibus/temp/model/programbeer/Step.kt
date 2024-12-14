package com.kardibus.temp.model.programbeer

import com.kardibus.temp.model.AuditListener
import com.kardibus.temp.model.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime

/**
 * Шаг для программы
 */
@Entity
@EntityListeners(AuditListener::class)
@Table(
    name = "step",
    uniqueConstraints = [
        UniqueConstraint(name = "uniqueConstraint_id_step", columnNames = ["id", "step"]),
    ],
)
open class Step : BaseEntity() {
    /** Шаг */
    @Column(name = "step", nullable = false)
    @NotNull
    open var step: Int = 0

    /** Время выполнения */
    @Column(name = "time")
    open var time: Int = 0

    /** Дата начала */
    @Column(name = "date_start")
    open var dateStart: LocalDateTime? = null

    /** Дата окончания */
    @Column(name = "date_end")
    open var dateEnd: LocalDateTime? = null

    /** Признак выполненого шага */
    @Column(name = "done")
    open var done: Boolean = false

    /** Признак работы */
    @Column(name = "work")
    open var work: Boolean = false

    /** Температура шага */
    @Column(name = "temp")
    open var temp: Double = 0.0
}
