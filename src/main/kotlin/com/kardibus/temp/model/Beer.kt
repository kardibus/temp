package com.kardibus.temp.model

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Beer {
    @Id
    var id: Long = 0
    var temp: Double = 0.0
    override fun toString(): String {
        return "Beer(id=$id, temp=$temp)"
    }
}