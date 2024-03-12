plugins {
    kotlin("jvm") version "1.9.0"
    application
    id("org.openjfx.javafxplugin") version "0.1.0"
}

group = "pl.lodz.p.edu"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
    compilerOptions.optIn.apply {
        add("kotlin.ExperimentalStdlibApi")
        add("kotlin.ExperimentalUnsignedTypes")
    }
}

application {
    mainClass.set("MainKt")
}

javafx {
    version = "21.0.2"
    modules("javafx.controls", "javafx.fxml")
}
