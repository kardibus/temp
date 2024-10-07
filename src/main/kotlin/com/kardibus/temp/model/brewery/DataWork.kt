package com.kardibus.temp.model.brewery

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID
import jdk.jfr.Label
import org.hibernate.annotations.UuidGenerator

/**
 * Данные для работы кипятильника
 */
@Entity
@Table(name = "data_work")
open class DataWork {

    @Id
    @GeneratedValue
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Column(name = "id", unique = true)
    open lateinit var id: UUID

    @Column(name = "program")
    @Label("Текущая программа")
    open var program: Long = 0

    @Column(name = "current_step")
    @Label("текущий шаг")
    open var currentStep: Long = 0

    @Column(name = "temp")
    @Label("Текущая температура")
    open var temp: Double = 0.0

    @Column(name = "work")
    @Label("Признак работы")
    open var work: Boolean = false
}
