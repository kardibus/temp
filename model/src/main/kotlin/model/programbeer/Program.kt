package model.programbeer

import jakarta.annotation.Nonnull
import model.AuditListener
import model.BaseEntity
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.FetchType
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

/**
 * Программа для пивоварни
 */
@Entity
@EntityListeners(AuditListener::class)
@Table(name = "program")
open class Program : BaseEntity() {
    /** Название программы */
    @Column(name = "name", nullable = false)
    @Nonnull
    open lateinit var name: String

    /** Признак работы */
    @Column(name = "work")
    open var work: Boolean = false

    /** Признак паузы */
    @Column(name = "pause")
    open var pause: Boolean = false

    /** Признак активной программы */
    @Column(name = "active")
    open var active: Boolean = false

    /** Ссылка на шаг */
    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.EAGER, mappedBy = "program")
    open lateinit var steps: MutableList<Step>

    override fun toString(): String {
        return "Program(name='$name', work=$work, pause=$pause, active=$active, steps=$steps)"
    }
}
