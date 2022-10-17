package com.kardibus.temp.model.programbeer

class Program {
    var id: Long? = null
    var name: String? = null
    var work: Boolean = false

    override fun toString(): String {
        return "Program(id=$id, name=$name, work=$work)"
    }
}