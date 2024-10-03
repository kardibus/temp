package com.kardibus.temp.repository

import com.kardibus.temp.model.brewery.DataWork
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface ModelRepository : JpaRepository<DataWork, Long> {
    @Query("select m.id, m.prog, m.curr, m.temp, m.work from model m limit 1 ", nativeQuery = true)
    fun findByProg(): Optional<DataWork>
}
