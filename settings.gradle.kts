plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "temp"
include("core-ktlint-rules")
include("kafka")
include("model")
include("action")
