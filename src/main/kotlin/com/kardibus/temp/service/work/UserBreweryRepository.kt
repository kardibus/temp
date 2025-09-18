package com.kardibus.temp.service.work

import java.util.UUID
import model.programbeer.UserBrewery
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserBreweryRepository : JpaRepository<UserBrewery, UUID>
