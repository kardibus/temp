package com.kardibus.temp.dto

import com.kardibus.temp.model.programbeer.Step

class ProgramDto {

    var id: Long? = null
    var name: String? = null
    var work: Boolean = false

    var steps: List<Step>? = null
}
