package com.kardibus.temp.model.programbeer

import com.kardibus.temp.model.AudiListener
import com.kardibus.temp.model.BaseEntity
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.FetchType
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
@EntityListeners(AudiListener::class)
@Table(name = "program")
open class Program: BaseEntity() {

    /** Название программы */
    @Column(name = "name", nullable = false)
    @NotNull
    open lateinit var name: String

    /** Признак работы */
    @Column(name = "work")
    open var work: Boolean = false

    /** Признак паузы */
    @Column(name = "pause")
    open var pause: Boolean = false

    /** Ссылка на шаг */
    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id")
    open lateinit var steps: MutableList<Step>
}
