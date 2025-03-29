package com.ktlint

import com.ktlint.rules.AlphabeticalConstructorRule
import com.ktlint.rules.ImportOrderRule
import com.pinterest.ktlint.cli.ruleset.core.api.RuleSetProviderV3
import com.pinterest.ktlint.rule.engine.core.api.RuleProvider
import com.pinterest.ktlint.rule.engine.core.api.RuleSetId

class RuleProvider : RuleSetProviderV3(RuleSetId("kardibus-rules")) {
    override fun getRuleProviders(): Set<RuleProvider> =
        setOf(
            RuleProvider { AlphabeticalConstructorRule() },
            RuleProvider { ImportOrderRule() },
        )
}
