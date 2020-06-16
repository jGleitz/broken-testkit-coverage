package de.joshuagleitze.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.task

class CoverageTestPlugin: Plugin<Project> {
	override fun apply(project: Project) {
		project.task("testTask") {
			doFirst {
				println("Test")
			}
		}
	}
}
