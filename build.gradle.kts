import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.jlleitschuh.gradle.ktlint") version "12.2.0"
    kotlin("jvm") version "2.1.0"
    id("org.jetbrains.kotlin.plugin.spring") version "2.1.20"
    id("io.spring.dependency-management") version "1.1.7"
}

repositories {
    mavenCentral()
    google()
    mavenLocal()
}

dependencies {

    ktlintRuleset(project(":core-ktlint-rules"))
    implementation(project(":model"))
    implementation(project(":kafka"))

    implementation("org.springframework.boot:spring-boot-starter:3.5.3")
    implementation("org.springframework.boot:spring-boot-starter-web:3.5.3")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.5.3")
    implementation("org.springframework.boot:spring-boot-starter-aop:3.5.3")

    implementation("jakarta.validation:jakarta.validation-api:3.1.1")

    implementation("org.springframework.kafka:spring-kafka:3.3.8")

    // implementation("org.springframework.boot:spring-boot-starter-cache")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.18.2")
    runtimeOnly("org.jetbrains.kotlin:kotlin-reflect:2.1.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:2.1.0")
    implementation("io.github.classgraph:classgraph:4.8.179")
    implementation("org.liquibase:liquibase-core:4.30.0")
    implementation("org.postgresql:postgresql:42.7.4")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.1")

    testImplementation("com.h2database:h2:2.3.232")
    testImplementation("org.springframework.boot:spring-boot-starter-test:3.5.3")
    testImplementation("org.assertj:assertj-core:3.27.0")
}

group = "com.kardibus"
version = "0.0.1-SNAPSHOT"
description = "temp"

tasks.withType<KotlinCompile>().configureEach {
    compilerOptions {
        freeCompilerArgs.add("-Xjsr305=strict")
        jvmTarget.set(JvmTarget.JVM_17)
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<Javadoc> {
    options.encoding = "UTF-8"
}

tasks {
//    test {
//        dependsOn(ktlintCheck)
//        mustRunAfter(ktlintCheck)
//        maxParallelForks = 3
//        useJUnitPlatform()
//    }
}

ktlint {
    filter {
        include("**/kotlin/**")
        include("**/test/**")
        exclude("**/build/**")
    }
}
