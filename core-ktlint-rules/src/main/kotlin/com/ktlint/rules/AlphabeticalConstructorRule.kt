package com.ktlint.rules

import com.pinterest.ktlint.rule.engine.core.api.ElementType
import com.pinterest.ktlint.rule.engine.core.api.Rule
import com.pinterest.ktlint.rule.engine.core.api.RuleId
import com.pinterest.ktlint.rule.engine.core.api.editorconfig.EditorConfig
import com.pinterest.ktlint.rule.engine.core.api.editorconfig.EditorConfigProperty
import org.ec4j.core.model.PropertyType
import org.jetbrains.kotlin.com.intellij.lang.ASTNode
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtPrimaryConstructor
import org.jetbrains.kotlin.psi.KtPsiFactory
import org.jetbrains.kotlin.psi.psiUtil.isAbstract

/**
 * Правило сортирует параметры конструктора по алфавиту
 * и сохраняет форматирование в виде столбика с запятой на каждой строке.
 * Типы классов для проверки задаются в `.editorconfig`.
 */
open class AlphabeticalConstructorRule :
    Rule(
        ruleId = RuleId("sorting-dependency-in-construct:alphabetical-constructor"),
        about = About(maintainer = "kardibus"),
        usesEditorConfigProperties =
            setOf(
                ALPHABETICAL_CONSTRACT,
                ALLOWED_CLASSES_PROPERTY,
                FORCE_MULTILINE_WHEN_PARAMETER_COUNT_GREATER_OR_EQUAL_THAN_PROPERTY,
            ),
    ) {
    // Список разрешённых типов классов
    private var allowedClasses: Set<String> = setOf()
    private var forceMultilineParameterCount: Int =
        FORCE_MULTILINE_WHEN_PARAMETER_COUNT_GREATER_OR_EQUAL_THAN_PROPERTY_UNSET

    /**
     * Метод вызывается перед анализом первого узла.
     * Здесь мы читаем настройки из `.editorconfig`.
     */
    override fun beforeFirstNode(editorConfig: EditorConfig) {
        allowedClasses = editorConfig[ALLOWED_CLASSES_PROPERTY]
            .split(",")
            .map { it.trim() }
            .toSet()

        forceMultilineParameterCount = editorConfig[FORCE_MULTILINE_WHEN_PARAMETER_COUNT_GREATER_OR_EQUAL_THAN_PROPERTY]
    }

    override fun beforeVisitChildNodes(
        node: ASTNode,
        autoCorrect: Boolean,
        emit: (offset: Int, errorMessage: String, canBeAutoCorrected: Boolean) -> Unit,
    ) {
        if (node.elementType == ElementType.PRIMARY_CONSTRUCTOR) {
            val constructor = node.psi as? KtPrimaryConstructor ?: return
            val parent = constructor.parent as? KtClass ?: return
            // Применяется только к классам, разрешённым в настройках
            if (!isClassAllowed(parent)) {
                return
            }

            val parameters = constructor.valueParameters
            val sortedParameters = parameters.sortedBy { it.name }
            if (parameters != sortedParameters) {
                emit(
                    node.startOffset,
                    "Параметры конструктора должны быть расположены в алфавитном порядке и в столбик.",
                    true,
                )

                if (autoCorrect) {
                    val psiFactory = KtPsiFactory(constructor.project)

                    val sortedParametersText =
                        if (parameters.size >= forceMultilineParameterCount) {
                            // Принудительно переводим в многострочный формат
                            sortedParameters.joinToString(",\n") { "    ${it.text}" }
                        } else {
                            // В одну строку
                            sortedParameters.joinToString(", ") { it.text }
                        }

                    val formattedParametersText =
                        if (parameters.size >= forceMultilineParameterCount) {
                            "(\n$sortedParametersText,\n)"
                        } else {
                            "($sortedParametersText)"
                        }

                    // Создаём новый список параметров
                    val newParameterList = psiFactory.createParameterList(formattedParametersText)

                    // Заменяем старый список параметров на новый
                    val valueParameterListNode = constructor.valueParameterList?.node
                    valueParameterListNode?.treeParent?.replaceChild(
                        valueParameterListNode,
                        newParameterList.node,
                    )
                }
            }
        }
    }

    /**
     * Проверяет, соответствует ли класс одному из разрешённых типов.
     */
    private fun isClassAllowed(klass: KtClass): Boolean {
        return when {
            klass.isEnum() && "enum" in allowedClasses -> true
            klass.isData() && "data" in allowedClasses -> true
            klass.isInterface() && "interface" in allowedClasses -> true
            klass.isAbstract() && "abstract" in allowedClasses -> true
            klass.isSealed() && "sealed" in allowedClasses -> true
            klass.modifierList?.hasModifier(org.jetbrains.kotlin.lexer.KtTokens.FINAL_KEYWORD) == true && "final" in allowedClasses -> true
            klass.name?.contains("Dto", ignoreCase = true) == true && "dto" in allowedClasses -> true
            "class" in allowedClasses -> true // Применяется к обычным классам
            else -> false
        }
    }

    companion object {
        private const val FORCE_MULTILINE_WHEN_PARAMETER_COUNT_GREATER_OR_EQUAL_THAN_PROPERTY_UNSET = Int.MAX_VALUE

        val ALPHABETICAL_CONSTRACT: EditorConfigProperty<Boolean> =
            EditorConfigProperty(
                type = PropertyType.LowerCasingPropertyType(
                    "kardibus_alphabetical_constructor",
                    "Включение отключение правила",
                    PropertyType.PropertyValueParser.BOOLEAN_VALUE_PARSER,
                ),
                defaultValue = false,
            )

        val ALLOWED_CLASSES_PROPERTY: EditorConfigProperty<String> =
            EditorConfigProperty(
                type =
                    PropertyType.LowerCasingPropertyType(
                        "kardibus_alphabetical_constructor_classes",
                        "Указывает, для каких типов классов применять сортировку параметров.",
                        PropertyType.PropertyValueParser.IDENTITY_VALUE_PARSER,
                        emptySet(),
                    ),
                defaultValue = "data,class,abstract,dto,enum",
                ktlintOfficialCodeStyleDefaultValue = "class",
            )

        val FORCE_MULTILINE_WHEN_PARAMETER_COUNT_GREATER_OR_EQUAL_THAN_PROPERTY: EditorConfigProperty<Int> =
            EditorConfigProperty(
                type =
                    PropertyType.LowerCasingPropertyType(
                        "kardibus_alphabetical_constructor_force_multiline_parameter_count",
                        "Определяет, после какого количества параметров они должны быть отформатированы в многострочном режиме.",
                        PropertyType.PropertyValueParser.POSITIVE_INT_VALUE_PARSER,
                        setOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "unset"),
                    ),
                defaultValue = FORCE_MULTILINE_WHEN_PARAMETER_COUNT_GREATER_OR_EQUAL_THAN_PROPERTY_UNSET,
                ktlintOfficialCodeStyleDefaultValue = 3,
            )
    }
}
