package de.joshuagleitze.gradle

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import org.gradle.testkit.runner.GradleRunner
import org.gradle.testkit.runner.TaskOutcome.SUCCESS
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.hamcrest.core.StringContains.containsString
import java.io.File

class CoveragePluginIntegrationTest {
	@TempDir
	lateinit var testProjectDir: File

	@Test
	fun `print test`() {
		testProjectDir.resolve("build.gradle.kts").appendText("""
			plugins {
				id("de.joshuagleitze.coverage-test")
			}
		""".trimIndent())

		val buildResult = GradleRunner.create()
			.withProjectDir(testProjectDir)
			.withPluginClasspath()
			.withJaCoCo()
			.forwardOutput()
			.withArguments("testTask")
			.build()

		assertThat(buildResult.task(":testTask")?.outcome, `is`(SUCCESS))
		assertThat(buildResult.output, containsString("test"))
	}

    fun GradleRunner.withJaCoCo() = apply {
		testProjectDir.resolve("gradle.properties")
			.appendBytes(javaClass.classLoader.getResourceAsStream("testkit-gradle.properties").readAllBytes())
    }
}
