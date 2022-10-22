package com.kardibus.temp.repository

import com.kardibus.temp.model.programbeer.Program
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.Optional

@org.springframework.stereotype.Repository
interface ProgramRepository : JpaRepository<Program, Long> {

    @Query("select prg.last_value from testincrement_sequence_program prg", nativeQuery = true)
    fun getIdProg(): Long

    @Query("select p.id, p.name, p.work, p.pause from Program p where p.work = true limit 1", nativeQuery = true)
    fun getProgEnable(): Optional<Program>
}
