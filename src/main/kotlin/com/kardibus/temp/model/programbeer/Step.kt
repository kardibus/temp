package com.kardibus.temp.model.programbeer

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Step {
    @Id
    @GeneratedValue
    var id: Long? = null
    var step: Long? = null
    var time: Int? = null

    override fun toString(): String {
        return "step{id=$id, step=$step, time=$time}"
    }
}