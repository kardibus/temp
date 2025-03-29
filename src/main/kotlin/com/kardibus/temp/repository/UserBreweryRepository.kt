package com.kardibus.temp.repository

import com.kardibus.temp.model.programbeer.UserBrewery
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.ListCrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface UserBreweryRepository : CrudRepository<UserBrewery, UUID>, ListCrudRepository<UserBrewery, UUID>
