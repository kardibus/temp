package com.kardibus.temp.dto.mapper

import com.kardibus.temp.dto.ModelDto
import com.kardibus.temp.model.Model

class MapperModelImp : MapperModel<Model, ModelDto> {

    override fun toModel(entity: Model): ModelDto {
        return ModelDto().apply {
            id = entity.id
            prog = entity.prog
            curr = entity.curr
            temp = entity.temp
            work = entity.work
        }
    }

    override fun fromModel(domain: ModelDto): Model {
        return Model().apply {
            id = domain.id
            prog = domain.prog
            curr = domain.curr
            temp = domain.temp
            work = domain.work
        }
    }
}
