package com.kardibus.temp.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Model {
    @Id
    @GeneratedValue
    var id: Long? = null
    var prog: Long = 0
    var curr: Long = 0
    var temp: Double = 0.0
    var work: Boolean = false
    override fun toString(): String {
        return "Model(prog=$prog, curr=$curr, temp=$temp, work=$work)"
    }
}
