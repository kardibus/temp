package com.kardibus.temp.repository

import com.kardibus.temp.model.programbeer.Step
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.ListCrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

@Repository
interface StepRepository : CrudRepository<Step, UUID> {

    @Modifying
    @Query("""delete from Step s where s.id = :id""")
    fun deleteStepById(id: UUID)
}
