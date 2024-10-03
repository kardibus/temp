package com.kardibus.temp.model

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
open class BaseModelUUID {

    @Id
    @GeneratedValue
    lateinit var id: UUID
}