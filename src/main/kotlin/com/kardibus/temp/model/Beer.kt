package com.kardibus.temp.model

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Beer {
    @Id
    @GeneratedValue
    var id: Long? = null
    var temp: Double = 0.0
    var date: LocalDateTime = LocalDateTime.now()
    override fun toString(): String {
        return "Beer(id=$id, temp=$temp)"
    }
}
