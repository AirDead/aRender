plugins {
    kotlin("jvm") version "2.0.0"
}

group = rootProject.group
version = rootProject.version

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

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = project.name
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