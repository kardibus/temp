package com.ktlint.rules

import com.pinterest.ktlint.test.KtLintAssertThat
import com.pinterest.ktlint.test.LintViolation
import org.junit.jupiter.api.Test

open class ImportOrderRuleTest {

    private val rule = KtLintAssertThat.assertThatRule { ImportOrderRule() }

    @Test
    fun test() {

        val code = """
        package com.kardibus.temp.service

        import com.kardibus.temp.model.programbeer.Program
        import com.kardibus.temp.model.programbeer.Step
        import com.kardibus.temp.model.programbeer.UserBrewery
        import com.kardibus.temp.repository.ProgramRepository
        import com.kardibus.temp.repository.UserBreweryRepository
        import org.junit.jupiter.api.Assertions.assertEquals
        import org.junit.jupiter.api.Assertions.assertFalse
        import org.junit.jupiter.api.DisplayName
        import org.junit.jupiter.api.Test
        import org.springframework.beans.factory.annotation.Autowired
        import org.springframework.boot.test.context.SpringBootTest
        import java.time.Clock
        import java.time.Instant
        import java.time.ZoneOffset

        class Example {}
        """.trimIndent()

        rule(code).withEditorConfigOverride(
            ImportOrderRule.IMPORT_ORDER to true,
            ImportOrderRule.IMPORT_EXCLUDE_LIST to "assertEquals,assertFalse"
        )
            .hasLintViolationsWithoutAutoCorrect(
                LintViolation(8, 1, """assertEquals требуется заменить на аналогичный из пакета org.assertj.core.api.Assertions"""),
                LintViolation(9, 1, """assertFalse требуется заменить на аналогичный из пакета org.assertj.core.api.Assertions""")
            )
    }
}
