package com.kardibus.temp.model.brewery

import jakarta.persistence.Column
import jakarta.persistence.Entity
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
@Table(name = "data")
open class Data {

    @Id
    @GeneratedValue
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Column(name = "id", unique = true)
    open lateinit var id: UUID

    @Column(name = "temp")
    @Label("Температура с датчика")
    open var temp: Double = 0.0

    @Column(name = "date")
    @Label("Дата получения температуры")
    open var date: LocalDateTime = LocalDateTime.now()
}