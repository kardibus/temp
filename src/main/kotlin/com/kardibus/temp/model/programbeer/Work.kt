package com.kardibus.temp.model.programbeer

import com.kardibus.temp.utils.common.Labeled

enum class Work(override val label: String) : Labeled {
    PAUSE("Пауза"),
    IN_PROGRESS("В работе"),
    WORK("Работает"),
}
