plugins {
    id("org.jetbrains.kotlin.plugin.spring") version "2.1.20"
    id("io.spring.dependency-management")
    kotlin("jvm") version "2.1.0"
}

group = "com.kardibus"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
    google()
    mavenLocal()
}

dependencies {

    implementation(project(":model"))

    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-aop:3.5.3")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.2")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.5.3")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.getByName<Jar>("jar") {
    enabled = true
}
