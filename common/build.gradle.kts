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
}

kotlin {
    jvmToolchain(17)
}