package com.kardibus.temp.repository

import com.kardibus.temp.model.brewery.Data
import java.util.UUID
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface DataRepository : CrudRepository<Data, UUID>
