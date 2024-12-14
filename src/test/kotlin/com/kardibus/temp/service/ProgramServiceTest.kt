package com.kardibus.temp.service

import com.kardibus.temp.common.CommonSpringTest
import com.kardibus.temp.model.programbeer.Program
import com.kardibus.temp.model.programbeer.Step
import com.kardibus.temp.model.programbeer.UserBrewery
import com.kardibus.temp.repository.ProgramRepository
import com.kardibus.temp.repository.UserBreweryRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.time.Clock
import java.time.Instant
import java.time.ZoneOffset

open class ProgramServiceTest
    @Autowired
    constructor(
        private var programRepository: ProgramRepository,
        private val userBreweryRepository: UserBreweryRepository,
    ) : CommonSpringTest() {
        @Test
        @DisplayName("Простая проверка работы калькулятора")
        fun calculateTimeWorkProgram() {
            val fixedInstant = Instant.parse("2024-01-01T00:00:00Z")
            val clock = Clock.fixed(fixedInstant, ZoneOffset.UTC)

            val programService = ProgramService(clock, programRepository)

            val step1 =
                Step().apply {
                    time = 1
                    step = 1
                    temp = 10.0
                    done = false
                    work = true
                }
            val step2 =
                Step().apply {
                    time = 1
                    step = 2
                    temp = 10.0
                    done = false
                    work = false
                }
            val program =
                Program().apply {
                    pause = false
                    work = true
                    name = "test"
                    steps = mutableListOf(step1, step2)
                }

            val userBrewery =
                UserBrewery().apply {
                    name = "test"
                    programs = mutableListOf(program)
                }

            userBreweryRepository.save(userBrewery)

            val result = programService.calculateTimeWorkProgram(userBrewery.id)

            assertEquals(false, result.steps.first().done)

            val advancedClock = Clock.fixed(fixedInstant.plusSeconds(61), ZoneOffset.UTC)
            val programServiceAdvanced = ProgramService(advancedClock, programRepository)

            val resultTwo = programServiceAdvanced.calculateTimeWorkProgram(userBrewery.id)

            assertEquals(true, resultTwo.steps.first().done)
        }

        @Test
        @DisplayName("Проверяем работу пазузы и перерасчет времени окончания работы шага")
        fun calculatePauseProgram() {
            val fixedInstant = Instant.parse("2024-01-01T00:00:00Z")
            val clock = Clock.fixed(fixedInstant, ZoneOffset.UTC)

            val programService = ProgramService(clock, programRepository)

            val step1 =
                Step().apply {
                    time = 1
                    step = 1
                    temp = 10.0
                    done = false
                    work = true
                }
            val step2 =
                Step().apply {
                    time = 1
                    step = 2
                    temp = 10.0
                    done = false
                    work = false
                }
            val program =
                Program().apply {
                    pause = true
                    work = true
                    name = "test"
                    steps = mutableListOf(step1, step2)
                }

            val userBrewery =
                UserBrewery().apply {
                    name = "test"
                    programs = mutableListOf(program)
                }

            userBreweryRepository.save(userBrewery)

            val result = programService.calculateTimeWorkProgram(userBrewery.id)

            assertEquals(false, result.steps.first().done)
            assertEquals(true, result.steps.first().work)

            assertEquals(true, result.pause)
            assertEquals(true, result.work)

            val clock2 = Clock.fixed(fixedInstant.plusSeconds(61), ZoneOffset.UTC)
            val programService2 = ProgramService(clock2, programRepository)
            val result2 = programService2.calculateTimeWorkProgram(userBrewery.id)

            assertEquals(false, result2.steps.first().done)
            assertEquals(true, result2.steps.first().work)

            assertEquals(true, result2.pause)
            assertEquals(true, result2.work)

            result2.pause = false

            programRepository.save(result2)

            val clock3 = Clock.fixed(fixedInstant.plusSeconds(91), ZoneOffset.UTC)
            val programService3 = ProgramService(clock3, programRepository)
            val result3 = programService3.calculateTimeWorkProgram(userBrewery.id)

            assertEquals(false, result3.steps.first().done)
            assertEquals(true, result3.steps.first().work)

            assertEquals(false, result3.pause)
            assertEquals(true, result3.work)

            val clock4 = Clock.fixed(fixedInstant.plusSeconds(121), ZoneOffset.UTC)
            val programService4 = ProgramService(clock4, programRepository)
            val result4 = programService4.calculateTimeWorkProgram(userBrewery.id)

            val clock5 = Clock.fixed(fixedInstant.plusSeconds(151), ZoneOffset.UTC)
            val programService5 = ProgramService(clock5, programRepository)
            val result5 = programService5.calculateTimeWorkProgram(userBrewery.id)

            assertEquals(true, result5.steps.first().done)
            assertEquals(false, result5.steps.first().work)

            assertEquals(false, result5.pause)
            assertEquals(true, result5.work)
        }
    }
