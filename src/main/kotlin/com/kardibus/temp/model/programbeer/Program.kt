package com.kardibus.temp.model.programbeer

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import jakarta.validation.constraints.NotNull
import java.util.UUID
import jdk.jfr.Label
import org.hibernate.annotations.UuidGenerator

/**
 * Программа для пивоварни
 */
@Entity
@Table(name = "program")
open class Program {

    @Id
    @GeneratedValue
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Column(name = "id", unique = true)
    open lateinit var id: UUID

    @Column(name = "name", nullable = false)
    @NotNull
    @Label("Название программы")
    open lateinit var name: String

    @Column(name = "work")
    @Label("Признак работы")
    open var work: Boolean = false

    @Column(name = "pause")
    @Label("Признак паузы")
    open var pause: Boolean = false

    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "program_id")
    @Label("Ссылка на шаг")
    open var steps: MutableList<Step>? = null
}
