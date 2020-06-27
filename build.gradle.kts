import org.gradle.api.JavaVersion.VERSION_1_9

plugins {
    kotlin("jvm") version "1.3.72"
    `kotlin-dsl`
	jacoco
    id("pl.droidsonroids.jacoco.testkit") version "1.0.7"
}

repositories {
	jcenter()
}

version = "1.0.0-SNAPSHOT"

java {
    sourceCompatibility = VERSION_1_9
    targetCompatibility = VERSION_1_9
}

gradlePlugin {
    plugins {
        create("coverage-test") {
            id = "de.joshuagleitze.coverage-test"
            implementationClass = "de.joshuagleitze.gradle.CoverageTestPlugin"
            version = project.version
        }
    }
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.2")
	testImplementation("org.hamcrest:java-hamcrest:2.0.0.0")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.2")
}

tasks.test {
    useJUnitPlatform()
}
