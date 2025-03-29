plugins {
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
    implementation("com.pinterest.ktlint:ktlint-cli-ruleset-core:0.50.0")
    implementation("com.pinterest.ktlint:ktlint-rule-engine-core:0.50.0")

    testImplementation("com.pinterest.ktlint:ktlint-test:0.50.0")

    testImplementation("org.junit.jupiter:junit-jupiter:5.10.1")
    testImplementation("org.assertj:assertj-core:3.27.0")

    testImplementation("org.slf4j:slf4j-simple:2.0.7")
}

kotlin {
    jvmToolchain(17)
}

tasks.test {
    useJUnitPlatform() // Включаем JUnit 5 Platform
    // Без этой строчки получаем java.lang.reflect.InaccessibleObjectException:
    // Unable to make field private transient java.lang.Object java.lang.Throwable.backtrace accessible:
    // module java.base does not “opens java.lang” to unnamed module
    jvmArgs = listOf("--add-opens=java.base/java.lang=ALL-UNNAMED")
}
