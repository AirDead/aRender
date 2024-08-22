plugins {
    kotlin("jvm") version "2.0.0"
}

group = "dev.airdead"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.joml:joml:1.10.8")
    compileOnly("org.jetbrains:annotations:24.1.0")
    implementation(kotlin("reflect"))
}

kotlin {
    jvmToolchain(17)
}