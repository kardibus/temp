package com.kardibus.temp.model

import jakarta.persistence.Column
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import java.time.LocalDateTime
import java.util.UUID
import org.hibernate.annotations.UuidGenerator

@MappedSuperclass
open class BaseEntity {

    @Id
    @GeneratedValue
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Column(name = "id", unique = true)
    open lateinit var id: UUID

    /** Дата получения температуры */
    @Column(name = "date")
    open lateinit var createdAt: LocalDateTime
}