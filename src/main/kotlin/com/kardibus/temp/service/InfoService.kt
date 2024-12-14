package com.kardibus.temp.service

import org.springframework.stereotype.Service
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping

@Service
open class InfoService(
    private val requestMappingHandlerMapping: RequestMappingHandlerMapping,
) {
    fun handlerMapping(): Map<String, List<String>> {
        val (disabled, enabled) =
            requestMappingHandlerMapping.handlerMethods.keys
                .flatMap { it.pathPatternsCondition?.patterns?.map { it.patternString } ?: emptySet() } // Получаем URL
                .distinct()
                .partition { url -> urls.any { url.startsWith(it) } }

        return mapOf("enabled" to enabled, "disabled" to disabled)
    }

    companion object {
        val urls = listOf("/data")
    }
}
