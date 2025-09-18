plugins {
    id("org.springframework.boot") version "3.5.3"
    id("org.jetbrains.kotlin.plugin.spring") version "2.1.20"
    id("io.spring.dependency-management")
    kotlin("jvm") version "2.1.0"
}

group = "com.kardibus"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
