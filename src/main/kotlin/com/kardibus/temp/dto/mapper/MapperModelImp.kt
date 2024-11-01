//package com.kardibus.temp.dto.mapper
//
//import com.kardibus.temp.dto.DataWorkDto
//
//class MapperModelImp : MapperModel<DataWork, DataWorkDto> {
//
//    override fun toModel(entity: DataWork): DataWorkDto {
//        return DataWorkDto(
//            entity.id,
//            entity.program,
//            entity.currentStep,
//            entity.temp,
//            entity.work,
//        )
//    }
//
//    override fun fromModel(domain: DataWorkDto): DataWork {
//        return DataWork().apply {
//            id = domain.id
//            program = domain.prog
//            currentStep = domain.curr
//            temp = domain.temp
//            work = domain.work
//        }
//    }
//}
