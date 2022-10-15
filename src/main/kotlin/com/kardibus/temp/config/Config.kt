package com.kardibus.temp.config

import io.swagger.v3.oas.annotations.info.Contact
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.info.License
import io.swagger.v3.oas.annotations.servers.Server
import io.swagger.v3.oas.models.OpenAPI
import org.springdoc.core.GroupedOpenApi
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.List


@Configuration
class Config {

    @Bean
    fun publicUserApi(): GroupedOpenApi? {
        return GroupedOpenApi.builder()
            .group("Users")
            .pathsToMatch("/users/**")
            .build()
    }

    @Bean
    fun customOpenApi(
        @Value("1") appDescription: String?,
        @Value("1") appVersion: String?
    ): OpenAPI? {
        return OpenAPI().info(io.swagger.v3.oas.models.info.Info().title("Hello World"))
    }
}
