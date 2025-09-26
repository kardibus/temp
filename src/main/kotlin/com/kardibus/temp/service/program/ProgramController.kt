package com.kardibus.temp.service.program

import com.kardibus.actionevent.annotation.ActionEventAnnotation
import com.kardibus.model.action.ActionName.*
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("program/v1")
class ProgramController(private val programService: ProgramService) {

    @GetMapping
    fun getProgram(): List<ProgramDto> = programService.getPrograms()

    @ActionEventAnnotation(name = PROGRAM)
    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    fun addProgram(@RequestBody programDto: ProgramDto) = programService.saveProgram(programDto = programDto)

    @ActionEventAnnotation(name = PROGRAM)
    @PostMapping("update")
    @ResponseStatus(HttpStatus.OK)
    fun updateProgram(@RequestBody programDto: ProgramDto) = programService.updateProgram(programDto = programDto)

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteProgram(@PathVariable id: UUID) = programService.deleteProgram(id = id)

    @PostMapping("work")
    @ResponseStatus(HttpStatus.UPGRADE_REQUIRED)
    fun changeWork(@RequestBody changeWork: ChangeWork) = programService.changeWork(changeWork.id, changeWork.work)

    @PostMapping("pause")
    @ResponseStatus(HttpStatus.UPGRADE_REQUIRED)
    fun changePause(@RequestBody changePause: ChangePause) = programService.changePause(changePause.id, changePause.pause)
}
