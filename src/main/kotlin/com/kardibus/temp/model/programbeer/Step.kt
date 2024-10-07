package com.kardibus.temp.model.programbeer

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime
import java.util.UUID
import jdk.jfr.Label
import org.hibernate.annotations.UuidGenerator

/**
 * Шаг для программы
 */
@Entity
@Table(name = "step")
open class Step {

    @Id
    @GeneratedValue
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Column(name = "id", unique = true)
    open lateinit var id: UUID

    @Column(name = "step", nullable = false)
    @NotNull
    @Label("Шаг")
    open var step: Int = 0

    @Column(name = "time")
    @Label("Время выполнения")
    open var time: Int = 0

    @Column(name = "date_start")
    @Label("Дата начала")
    open var dateStart: LocalDateTime? = null

    @Column(name = "date_end")
    @Label("Дата окончания")
    open var dateEnd: LocalDateTime? = null

    @Column(name = "done")
    @Label("Признак выполненого шага")
    open var done: Boolean = false

    @Column(name = "work")
    @Label("Признак работы")
    open var work: Boolean = false

    @Column(name = "temp")
    @Label("Температура шага")
    open var temp: Long = 0
}
