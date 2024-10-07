package com.kardibus.temp.service

import com.kardibus.temp.model.programbeer.Program
import com.kardibus.temp.model.programbeer.Step
import com.kardibus.temp.repository.ProgramRepository
import com.kardibus.temp.repository.StepRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.util.Assert

@SpringBootTest
internal class ProgramServiceTest @Autowired constructor(
    private val programRepository: ProgramRepository,
    private val programService: ProgramService,
    private val stepRepository: StepRepository,
) {

    @Test
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
       // stepRepository.save(step)
        programRepository.save(program)

        val result = programService.calculateTimeWorkProgram(program = program)

        assertEquals(false, result.steps?.first()?.done)

        Thread.sleep(60000)

        val resultTwo = programService.calculateTimeWorkProgram(program = result)

        assertEquals(true, resultTwo.steps?.first()?.done)
    }


}