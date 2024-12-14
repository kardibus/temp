package com.kardibus.temp.model.programbeer

import com.kardibus.temp.model.AudiListener
import com.kardibus.temp.model.BaseEntity
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Table(name = "user_brewery")
@EntityListeners(AudiListener::class)
@Entity
open class UserBrewery : BaseEntity() {

    /** Имя пользователя */
    @Column(name = "name", nullable = false)
    open lateinit var name: String

    /** Почта пользователя */
    @Column(name = "email", nullable = true)
    open lateinit var email: String

    /** Ссылка на программу */
    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_brewery_id")
    open var programs: MutableList<Program>? = null
}