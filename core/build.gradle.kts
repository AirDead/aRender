plugins {
    kotlin("jvm") version "2.0.0"
    id("fabric-loom") version "1.7-SNAPSHOT"
}

group = "dev.airdead"
version = "1.0.0"

repositories {
    mavenCentral()
    mavenLocal()
    maven {
        name = "Fabric"
        url = uri("https://maven.fabricmc.net/")
    }
}

dependencies {
    // Minecraft, Mappings, Fabric Loader
    minecraft("com.mojang:minecraft:${project.findProperty("minecraft_version")}")
    mappings("net.fabricmc:yarn:${project.findProperty("yarn_mappings")}:v2")
    modImplementation("net.fabricmc:fabric-loader:${project.findProperty("loader_version")}")

    // Fabric API
    modImplementation("net.fabricmc.fabric-api:fabric-api:${project.findProperty("fabric_version")}")
    modImplementation("net.fabricmc:fabric-language-kotlin:1.12.0+kotlin.2.0.10")

    implementation(project(":common"))
}

tasks.processResources {
    inputs.property("version", project.version)

    filesMatching("fabric.mod.json") {
        expand("version" to project.version)
    }
}

kotlin {
    jvmToolchain(17)
}