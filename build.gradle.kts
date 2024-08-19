plugins {
	kotlin("jvm") version "2.0.0"
}

group = "dev.airdead"
version = "1.0.0"

repositories {
	mavenCentral()
}

allprojects {
	apply(plugin = "org.jetbrains.kotlin.jvm")
}

dependencies {
	// Minecraft, Mappings, Fabric Loader
	//minecraft("com.mojang:minecraft:${project.findProperty("minecraft_version")}")
	//mappings("net.fabricmc:yarn:${project.findProperty("yarn_mappings")}:v2")
	//modImplementation("net.fabricmc:fabric-loader:${project.findProperty("loader_version")}")

	// Fabric API
	//modImplementation("net.fabricmc.fabric-api:fabric-api:${project.findProperty("fabric_version")}")
	//modImplementation("net.fabricmc.fabric-language-kotlin:${project.findProperty("fabric_kotlin_version")}")
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
