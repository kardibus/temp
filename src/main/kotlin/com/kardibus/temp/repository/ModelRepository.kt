package com.kardibus.temp.repository

import com.kardibus.temp.model.Model
import org.springframework.data.jpa.repository.JpaRepository

@org.springframework.stereotype.Repository
interface ModelRepository : JpaRepository<Model, Long>
