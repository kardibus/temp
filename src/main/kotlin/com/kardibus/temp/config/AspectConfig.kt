package com.kardibus.temp.config

import com.kardibus.temp.controller.InfoController
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
            val bean = beanFactory.getBean(InfoController::class.java)
            println(">>> InfoController class: ${bean.javaClass.name}")
        }
    }
}
