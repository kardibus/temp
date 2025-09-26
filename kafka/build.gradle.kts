plugins {
    id("org.springframework.boot") version "3.5.3"
    id("org.jetbrains.kotlin.plugin.spring") version "2.1.20"
    id("io.spring.dependency-management")
    kotlin("jvm") version "2.1.0"
    id("org.hibernate.orm") version "7.1.1.Final"
}

group = "com.kardibus"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
    google()
    mavenLocal()
}

dependencies {
    // Spring + Kafka
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.kafka:spring-kafka")

    // Jackson
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // AOP, если используешь @KafkaListener
    implementation("org.springframework.boot:spring-boot-starter-aop")

    // Тесты
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.kafka:spring-kafka-test")
}

//kotlin {
//    jvmToolchain(17)
//}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

tasks.test {
    useJUnitPlatform()
}
