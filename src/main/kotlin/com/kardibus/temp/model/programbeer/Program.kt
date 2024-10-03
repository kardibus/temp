package com.kardibus.temp.model.programbeer

import com.kardibus.temp.model.BaseModelUUID
import jdk.jfr.Label
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.validation.constraints.NotNull

/**
 * Программа для пивоварни
 */
@Entity
@Table(name = "program")
open class Program : BaseModelUUID() {

    @Column(name = "name", nullable = false)
    @NotNull
    @Label("Название программы")
    lateinit var name: String

    @Column(name = "work")
    @Label("Признак работы")
    var work: Boolean = false

    @Column(name = "pause")
    @Label("Признак паузы")
    var pause: Boolean = false

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "step_id", nullable = false)
    @NotNull
    @Label("Ссылка на шаг")
    lateinit var step: Step
}
