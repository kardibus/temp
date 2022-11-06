package com.kardibus.temp.model.programbeer

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Step {
    @Id
    @GeneratedValue
    var id: Long? = null
    var step: Long? = null
    var time: Long? = null

    @Column(name = "from_Date")
    var fromDate: LocalDateTime? = null

    @Column(name = "to_Date")
    var toDate: LocalDateTime? = null
    var done: Boolean = false
    var temp: Long = 0
    var prog_id: Long? = null

    override fun toString(): String {
        return "step{id=$id, step=$step, time=$time}"
    }
}
