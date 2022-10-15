package com.kardibus.temp

import com.kardibus.temp.model.Model
import org.springframework.data.jpa.repository.JpaRepository

@org.springframework.stereotype.Repository
interface Repository : JpaRepository<Model, Long>