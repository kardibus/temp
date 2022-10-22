package com.kardibus.temp.repository

import com.kardibus.temp.model.Model
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.Optional

@org.springframework.stereotype.Repository
interface ModelRepository : JpaRepository<Model, Long>{
    @Query("select m.id, m.prog, m.curr, m.temp, m.work from model m limit 1 ", nativeQuery = true)
    fun findByProg():Optional<Model>
}
