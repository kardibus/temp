package com.kardibus.temp.model.brewery

import com.kardibus.temp.model.BaseModelUUID
import jdk.jfr.Label
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

/**
 * Данные от кипятильника
 */
@Entity
@Table(name = "data")
class Data : BaseModelUUID() {

    @Column(name = "temp")
    @Label("Температура с датчика")
    var temp: Double = 0.0

    @Column(name = "date")
    @Label("Дата получения температуры")
    var date: LocalDateTime = LocalDateTime.now()
}
