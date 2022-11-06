package com.kardibus.temp.model.programbeer

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Program {
    @Id
    @GeneratedValue
    var id: Long? = null
    var name: String? = null
    var work: Boolean = false
    var pause: Boolean = true

    override fun toString(): String {
        return "Program(id=$id, name=$name, work=$work)"
    }
}
