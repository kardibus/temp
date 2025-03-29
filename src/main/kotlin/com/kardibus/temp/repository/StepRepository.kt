package com.kardibus.temp.repository

import com.kardibus.temp.model.programbeer.Step
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.ListCrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface StepRepository : CrudRepository<Step, UUID>, ListCrudRepository<Step, UUID>
