import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "1.9.22"
    kotlin("plugin.spring") version "1.9.22"
    kotlin("plugin.jpa") version "1.9.22"
    id("org.springframework.boot") version "3.4.0"
}

repositories {
    mavenCentral()
    maven {
        url = uri("https://company/com/maven2")
    }
    mavenLocal()
    flatDir {
        dirs("libs")
    }
}

dependencies {

    implementation("org.springframework.boot:spring-boot-starter-web")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    implementation("org.springframework.boot:spring-boot-starter-cache")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.18.2")
    runtimeOnly("org.jetbrains.kotlin:kotlin-reflect:2.1.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:2.1.0")
    implementation("org.springdoc:springdoc-openapi-ui:1.8.0")
    implementation("io.github.classgraph:classgraph:4.8.179")

    implementation("org.liquibase:liquibase-core:4.30.0")

    implementation("org.springframework.boot:spring-boot-starter-freemarker:3.4.0")

    testImplementation("com.h2database:h2:2.3.232")
    implementation("org.postgresql:postgresql:42.7.4")

    testImplementation("org.springframework.boot:spring-boot-starter-test:3.4.0")
}

group = "com.kardibus"
version = "0.0.1-SNAPSHOT"
description = "temp"
java.sourceCompatibility = JavaVersion.VERSION_17

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
    }
}

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}

tasks.withType<Javadoc>() {
    options.encoding = "UTF-8"
}
