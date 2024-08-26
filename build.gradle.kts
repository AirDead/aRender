plugins {
	kotlin("jvm") version "2.0.0"
	id("maven-publish")
}

group = "dev.airdead.arender"
version = "0.1.7-dev10"

repositories {
	mavenCentral()
}

allprojects {
	apply(plugin = "org.jetbrains.kotlin.jvm")
	apply(plugin = "maven-publish")
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