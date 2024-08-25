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

tasks.processResources {
	inputs.property("version", project.version)

	filesMatching("fabric.mod.json") {
		expand("version" to project.version)
	}
}

kotlin {
	jvmToolchain(17)
}
