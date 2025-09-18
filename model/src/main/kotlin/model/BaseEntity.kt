package model

import jakarta.persistence.Column
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import org.hibernate.annotations.UuidGenerator
import java.time.LocalDateTime
import java.util.UUID

@MappedSuperclass
abstract class BaseEntity {
    @Id
    @UuidGenerator
    @Column(name = "id", unique = true)
    open var id: UUID? = null

    /** Дата получения температуры */
    @Column(name = "createdAt")
    open lateinit var createdAt: LocalDateTime
}
