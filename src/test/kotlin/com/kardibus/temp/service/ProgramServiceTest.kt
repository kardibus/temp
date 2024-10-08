package com.kardibus.temp.service

import com.kardibus.temp.model.programbeer.Program
import com.kardibus.temp.model.programbeer.Step
import com.kardibus.temp.repository.ProgramRepository
import com.kardibus.temp.repository.StepRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class ProgramServiceTest @Autowired constructor(
    private val programRepository: ProgramRepository,
    private val programService: ProgramService,
    private val stepRepository: StepRepository,
) {

    @Test
    @DisplayName("")
    fun calculateTimeWorkProgram() {
        val step = Step().apply {
            time = 1
            step = 1
            temp = 10
            done = false
            work = true
        }
        val program = Program().apply {
            pause = false
            work = true
            name = "test"
            steps = mutableListOf(step)
        }

        programRepository.save(program)

        val result = programService.calculateTimeWorkProgram(program = program)

        assertEquals(false, result.steps?.first()?.done)

        Thread.sleep(60000)

        val resultTwo = programService.calculateTimeWorkProgram(program = result)

        assertEquals(true, resultTwo.steps?.first()?.done)
    }

    @Test
    @DisplayName("Проверяем работу пазузы и перерасчет времени окончания работы шага")
    fun calculatePauseProgram() {
        val step = Step().apply {
            time = 1
            step = 1
            temp = 10
            done = false
            work = true
        }
        val program = Program().apply {
            pause = true
            work = true
            name = "test"
            steps = mutableListOf(step)
        }

        programRepository.save(program)

        val result = programService.calculateTimeWorkProgram(program = program)

        assertEquals(false, result.steps?.first()?.done)
        assertEquals(true, result.steps?.first()?.work)

        assertEquals(true, result.pause)
        assertEquals(true, result.work)

        Thread.sleep(60000)

        val result2 = programService.calculateTimeWorkProgram(program = result)

        assertEquals(false, result2.steps?.first()?.done)
        assertEquals(true, result2.steps?.first()?.work)

        assertEquals(true, result2.pause)
        assertEquals(true, result2.work)

        Thread.sleep(30000)
        result2.pause = false
        val result3 = programService.calculateTimeWorkProgram(program = result2)

        assertEquals(false, result3.steps?.first()?.done)
        assertEquals(true, result3.steps?.first()?.work)

        assertEquals(false, result3.pause)
        assertEquals(true, result3.work)

        val result4 = programService.calculateTimeWorkProgram(program = result3)
        Thread.sleep(30000)

        val result5 = programService.calculateTimeWorkProgram(program = result4)

        assertEquals(true, result5.steps?.first()?.done)
        assertEquals(false, result5.steps?.first()?.work)

        assertEquals(false, result5.pause)
        assertEquals(true, result5.work)
    }
}