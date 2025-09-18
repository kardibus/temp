package model.programbeer

import jakarta.annotation.Nonnull
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDateTime
import model.AuditListener
import model.BaseEntity

/**
 * Шаг для программы
 */
@Entity
@EntityListeners(AuditListener::class)
@Table(
    name = "step",
//    uniqueConstraints = [
//        UniqueConstraint(name = "uniqueConstraint_id_step", columnNames = ["id", "step"]),
//    ],
)
open class Step : BaseEntity() {
    /** Шаг */
    @Column(name = "step", nullable = false)
    @Nonnull
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

    @ManyToOne
    @JoinColumn(name = "program_id")
    open var program: Program? = null

    /** Температура шага */
    @Column(name = "temp")
    open var temp: Double = 0.0

    override fun toString(): String {
        return "Step(step=$step, time=$time, dateStart=$dateStart, dateEnd=$dateEnd, done=$done, work=$work, temp=$temp)"
    }
}
