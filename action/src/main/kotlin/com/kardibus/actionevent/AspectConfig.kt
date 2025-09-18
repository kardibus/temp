package com.kardibus.actionevent

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableAspectJAutoProxy

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
class AspectConfig {
    @Bean
    fun logProxyType(beanFactory: ConfigurableListableBeanFactory): CommandLineRunner {
        return CommandLineRunner {
            println(">>> InfoController class: ${javaClass.name}")
        }
    }
}
