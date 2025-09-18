package model.programbeer

import model.Labeled

enum class Work(override val label: String) : Labeled {
    PAUSE("Пауза"),
    IN_PROGRESS("В работе"),
    WORK("Работает"),
}
