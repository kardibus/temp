package com.kardibus.temp.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.validation.constraints.NotNull

@Entity
class Beer {
    @Id
    @GeneratedValue
    var id: Long? = null
    var temp: Double = 0.0
    override fun toString(): String {
        return "Beer(id=$id, temp=$temp)"
    }
}