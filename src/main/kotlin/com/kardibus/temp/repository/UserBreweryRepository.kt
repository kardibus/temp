package com.kardibus.temp.repository

import com.kardibus.temp.model.programbeer.UserBrewery
import java.util.UUID
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserBreweryRepository : JpaRepository<UserBrewery, UUID>