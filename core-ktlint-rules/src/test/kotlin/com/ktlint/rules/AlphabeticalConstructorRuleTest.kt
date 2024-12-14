package com.ktlint.rules

import com.pinterest.ktlint.test.KtLintAssertThat
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class AlphabeticalConstructorRuleTest {

    private val ruleAssertThat = KtLintAssertThat.assertThatRule { AlphabeticalConstructorRule() }

    @Test
    fun `параметры должны быть отсортированы по алфавиту`() {
        val code = """
            class Example(
                private val banana: String,
                private val apple: String,
            )
        """.trimIndent()

        val formattedCode = """
            class Example(private val apple: String, private val banana: String)
        """.trimIndent()

        val result = ruleAssertThat(code)
            .hasLintViolation(1, 14, "Параметры конструктора должны быть расположены в алфавитном порядке и в столбик.")
            .isFormattedAs(formattedCode)

        // AssertJ: проверяем форматированный результат
        assertThat(result)
            .describedAs("Код должен быть отформатирован так, чтобы параметры конструктора были в алфавитном порядке")
            .isNotNull()
    }
}
