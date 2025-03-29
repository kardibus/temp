package com.ktlint.rules

import com.pinterest.ktlint.rule.engine.core.api.ElementType
import com.pinterest.ktlint.rule.engine.core.api.Rule
import com.pinterest.ktlint.rule.engine.core.api.RuleId
import com.pinterest.ktlint.rule.engine.core.api.editorconfig.EditorConfig
import com.pinterest.ktlint.rule.engine.core.api.editorconfig.EditorConfigProperty
import org.ec4j.core.model.PropertyType
import org.ec4j.core.model.PropertyType.PropertyValueParser
import org.jetbrains.kotlin.com.intellij.lang.ASTNode
import org.jetbrains.kotlin.psi.KtImportList

class ImportOrderRule : Rule(
    ruleId = RuleId("import-order:import-order"),
    about = About(maintainer = "kardibus"),
    usesEditorConfigProperties = setOf(IMPORT_ORDER, IMPORT_EXCLUDE_LIST),
) {

    private var importOrder: Boolean = true
    private var excludetList: Set<String> = setOf()

    override fun beforeVisitChildNodes(
        node: ASTNode,
        autoCorrect: Boolean,
        emit: (offset: Int, errorMessage: String, canBeAutoCorrected: Boolean) -> Unit
    ) {
        if (!importOrder) {
            return
        }

        if (node.elementType == ElementType.IMPORT_LIST) {
            val importList = (node.psi as KtImportList).imports

            importList.forEach { importDirective ->
                val importPath = importDirective.importPath?.importedName?.asString()

                if (importDirective.text.contains("junit") && importPath in excludetList) {
                    emit(
                        importDirective.node.startOffset,
                        "${importPath} требуется заменить на аналогичный из пакета org.assertj.core.api.Assertions",
                        false
                    )
                }
            }
        }
    }

    override fun beforeFirstNode(editorConfig: EditorConfig) {
        excludetList = editorConfig[IMPORT_EXCLUDE_LIST].split(",")
            .map { it.trim() }
            .filter { it.isNotEmpty() }
            .toSet()

        importOrder = editorConfig[IMPORT_ORDER]
    }

    companion object {
        val IMPORT_ORDER: EditorConfigProperty<Boolean> = EditorConfigProperty(
            type = PropertyType.LowerCasingPropertyType(
                "kardibus_import_exclude",
                "Включение отключение правила",
                PropertyValueParser.BOOLEAN_VALUE_PARSER,
            ),
            defaultValue = true,
        )

        val IMPORT_EXCLUDE_LIST: EditorConfigProperty<String> =
            EditorConfigProperty(
                type =
                    PropertyType.LowerCasingPropertyType(
                        "kardibus_import_exclude_list",
                        "Указывает, какие Assertions запрещено использовать из пакета junit.",
                        PropertyValueParser.IDENTITY_VALUE_PARSER,
                    ),
                defaultValue = "assertEquals",
            )
    }
}
