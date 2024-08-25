plugins {
    kotlin("jvm") version "2.0.0"
    id("fabric-loom") version "1.6-SNAPSHOT"
}

group = "dev.airdead.arender"
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

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = "core"
            version = project.version.toString()

            from(components["java"])

            pom {
                developers {
                    developer {
                        id.set("axiefeat")
                        name.set("Axiefeat")
                    }
                    developer {
                        id.set("pivnaa.bochka")
                        name.set("AirDead")
                    }
                }
            }
        }
    }
}

