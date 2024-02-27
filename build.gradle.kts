plugins {
    kotlin("jvm") version "1.9.0"
    application
}

group = "pl.lodz.p.edu"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
    compilerOptions.optIn.add("kotlin.ExperimentalStdlibApi")
}

application {
    mainClass.set("MainKt")
}
