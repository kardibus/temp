package com.kardibus.temp.model.brewery

import com.kardibus.temp.model.BaseModelUUID
import jdk.jfr.Label
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

/**
 * Данные для работы кипятильника
 */
@Entity
@Table(name = "data_work")
class DataWork : BaseModelUUID() {

    @Column(name = "program")
    @Label("Текущая программа")
    var program: Long = 0

    @Column(name = "current_step")
    @Label("текущий шаг")
    var currentStep: Long = 0

    @Column(name = "temp")
    @Label("Текущая температура")
    var temp: Double = 0.0

    @Column(name = "work")
    @Label("Признак работы")
    var work: Boolean = false

}
