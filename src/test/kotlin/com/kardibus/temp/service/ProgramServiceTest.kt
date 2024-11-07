package com.kardibus.temp.service

import com.kardibus.temp.model.programbeer.Program
import com.kardibus.temp.model.programbeer.Step
import com.kardibus.temp.repository.ProgramRepository
import java.time.Clock
import java.time.Instant
import java.time.ZoneOffset
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class ProgramServiceTest @Autowired constructor(
    private val programRepository: ProgramRepository
) {

    @Test
    @DisplayName("Простая проверка работы калькулятора")
    fun calculateTimeWorkProgram() {
        val fixedInstant = Instant.parse("2024-01-01T00:00:00Z")
        val clock = Clock.fixed(fixedInstant, ZoneOffset.UTC)

        val programService = ProgramService(programRepository, clock)

        val step1 = Step().apply {
            time = 1
            step = 1
            temp = 10.0
            done = false
            work = true
        }
        val step2 = Step().apply {
            time = 1
            step = 2
            temp = 10.0
            done = false
            work = false
        }
        val program = Program().apply {
            pause = false
            work = true
            name = "test"
            steps = mutableListOf(step1,step2)
        }

        programRepository.save(program)

        val result = programService.calculateTimeWorkProgram(program.id)

        assertEquals(false, result.steps.first().done)

        val advancedClock = Clock.fixed(fixedInstant.plusSeconds(61), ZoneOffset.UTC)
        val programServiceAdvanced = ProgramService(programRepository, advancedClock)

        val resultTwo = programServiceAdvanced.calculateTimeWorkProgram(result.id)

        assertEquals(true, resultTwo.steps.first().done)
    }

    @Test
    @DisplayName("Проверяем работу пазузы и перерасчет времени окончания работы шага")
    fun calculatePauseProgram() {
        val fixedInstant = Instant.parse("2024-01-01T00:00:00Z")
        val clock = Clock.fixed(fixedInstant, ZoneOffset.UTC)

        val programService = ProgramService(programRepository, clock)

        val step1 = Step().apply {
            time = 1
            step = 1
            temp = 10.0
            done = false
            work = true
        }
        val step2 = Step().apply {
            time = 1
            step = 2
            temp = 10.0
            done = false
            work = false
        }
        val program = Program().apply {
            pause = true
            work = true
            name = "test"
            steps = mutableListOf(step1,step2)
        }

        programRepository.save(program)

        val result = programService.calculateTimeWorkProgram(program.id)

        assertEquals(false, result.steps.first().done)
        assertEquals(true, result.steps.first().work)

        assertEquals(true, result.pause)
        assertEquals(true, result.work)

        val clock2 = Clock.fixed(fixedInstant.plusSeconds(61), ZoneOffset.UTC)
        val programService2 = ProgramService(programRepository, clock2)
        val result2 = programService2.calculateTimeWorkProgram(result.id)

        assertEquals(false, result2.steps.first().done)
        assertEquals(true, result2.steps.first().work)

        assertEquals(true, result2.pause)
        assertEquals(true, result2.work)

        result2.pause = false

        programRepository.save(result2)

        val clock3 = Clock.fixed(fixedInstant.plusSeconds(91), ZoneOffset.UTC)
        val programService3 = ProgramService(programRepository, clock3)
        val result3 = programService3.calculateTimeWorkProgram(result2.id)

        assertEquals(false, result3.steps.first().done)
        assertEquals(true, result3.steps.first().work)

        assertEquals(false, result3.pause)
        assertEquals(true, result3.work)

        val clock4 = Clock.fixed(fixedInstant.plusSeconds(121), ZoneOffset.UTC)
        val programService4 = ProgramService(programRepository, clock4)
        val result4 = programService4.calculateTimeWorkProgram(result3.id)

        val clock5 = Clock.fixed(fixedInstant.plusSeconds(151), ZoneOffset.UTC)
        val programService5 = ProgramService(programRepository, clock5)
        val result5 = programService5.calculateTimeWorkProgram(result4.id)

        assertEquals(true, result5.steps.first().done)
        assertEquals(false, result5.steps.first().work)

        assertEquals(false, result5.pause)
        assertEquals(true, result5.work)
    }
}