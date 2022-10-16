package com.kardibus.temp.config

import io.swagger.v3.oas.models.OpenAPI
import org.springdoc.core.GroupedOpenApi
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class Config {

    @Bean
    fun publicUserApi(): GroupedOpenApi? {
        return GroupedOpenApi.builder()
            .group("Beer")
            .pathsToMatch("/users/**")
            .build()
    }

    @Bean
    fun customOpenApi(
        @Value("Пивоварня") appDescription: String?,
        @Value("1.0.0") appVersion: String?
    ): OpenAPI? {
        return OpenAPI().info(io.swagger.v3.oas.models.info.Info().title("Пивоварня"))
    }
}
