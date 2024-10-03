package com.kardibus.temp.model.programbeer

import com.kardibus.temp.model.BaseModelUUID
import jdk.jfr.Label
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table
import javax.validation.constraints.NotNull

/**
 * Шаг для программы
 */
@Entity
@Table(name = "step")
open class Step : BaseModelUUID() {

    @Column(name = "step", nullable = false)
    @NotNull
    @Label("Шаг")
    var step: Int = 0

    @Column(name = "time")
    @Label("Время выполнения")
    var time: Int = 0

    @Column(name = "date_start")
    @Label("Дата начала")
    var dateStart: LocalDateTime? = LocalDateTime.now()

    @Column(name = "date_end", nullable = false)
    @NotNull
    @Label("Дата окончания")
    lateinit var dateEnd: LocalDateTime

    @Column(name = "done")
    @Label("Признак выполненого шага")
    var done: Boolean = false

    @Column(name = "temp")
    @Label("Температура шага")
    var temp: Long = 0
}
