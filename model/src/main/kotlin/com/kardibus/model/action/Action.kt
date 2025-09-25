package com.kardibus.model.action

import com.kardibus.model.AuditListener
import com.kardibus.model.BaseEntity
import com.kardibus.model.programbeer.UserBrewery
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@EntityListeners(AuditListener::class)
@Table(name = "action")
class Action : BaseEntity() {

    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: UserBrewery? = null

    @Column(name = "action")
    lateinit var action: ActionName

    @Column(name = "description")
    var description: String? = null

    @Column(name = "status")
    var status: ActionStatus = ActionStatus.CREATED

    @Column(name = "completionDate")
    var completionDate: LocalDateTime? = null

    @Column(name = "input")
    var input: ByteArray? = null

    @Column(name = "output")
    var output: ByteArray? = null
}
