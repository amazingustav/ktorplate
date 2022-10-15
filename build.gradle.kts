import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val javaVersion = findProperty("java.version").toString().toInt()
val kotlinVersion = findProperty("kotlin.version")
val koinVersion = findProperty("koin.version")
val configTypesafeVersion = findProperty("config.typesafe.version")
val jbcryptVersion = findProperty("jbcrypt.version")
val logbackVersion = findProperty("logback.version")
val junitVersion = findProperty("junit.version")

plugins {
    kotlin("jvm") version "1.3.72"
}

allprojects {
    apply {
        plugin("kotlin")
    }

    repositories {
        mavenCentral()
        google()
    }
}

subprojects {
    java.sourceCompatibility = JavaVersion.toVersion(javaVersion)
    java.targetCompatibility = JavaVersion.toVersion(javaVersion)

    version = "1.0.0"
    group = "br.com.amz"

    sourceSets {
        main { java.setSrcDirs(listOf("src/main/kotlin")) }
        test { java.setSrcDirs(listOf("src/test/kotlin")) }
    }

    kotlin {
        sourceSets {
            main { kotlin.setSrcDirs(listOf("src/main/kotlin")) }
            test { kotlin.setSrcDirs(listOf("src/test/kotlin")) }
        }
    }

    dependencies {
        implementation(kotlin("stdlib"))
        implementation(kotlin("reflect"))

        implementation("com.typesafe:config:$configTypesafeVersion")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinVersion")
        implementation("io.insert-koin:koin-core:$koinVersion")
        implementation("io.insert-koin:koin-core-ext:$koinVersion")
        implementation("org.mindrot:jbcrypt:$jbcryptVersion")
        implementation("ch.qos.logback:logback-classic:$logbackVersion")

        testImplementation("junit:junit:$junitVersion")
    }

    val modulesToExcludeFromTesting = listOf("entity")
    val patternsToExcludeFromTesting = listOf("**/config/**")

    tasks {
        compileJava {
            options.encoding = "UTF-8"
        }

        withType<KotlinCompile>().configureEach {
            kotlinOptions {
                freeCompilerArgs = listOf("-Xjvm-default=enable")
                allWarningsAsErrors = true
                jvmTarget = "$javaVersion"
                apiVersion = "1.6"
                languageVersion = "1.6"
            }
        }

        compileTestKotlin {
            kotlinOptions {
                jvmTarget = "$javaVersion"
            }
        }

        test {
            useJUnitPlatform()

            onlyIf { project.name !in modulesToExcludeFromTesting }
            excludes.addAll(patternsToExcludeFromTesting)

            reports {
                html.apply {
                    outputLocation.set(unitTestReportingDirectory.dir("html").asFile)
                    required.set(true)
                }

                junitXml.apply {
                    outputLocation.set(unitTestReportingDirectory.dir("xml").asFile)
                    required.set(true)
                    isOutputPerTestCase = false
                    mergeReruns.set(true)
                }
            }
        }
    }
}

tasks {
    check {
        setDependsOn(subprojects.map { it.getTasksByName("check", false) })
    }
}

val Project.reportingDirectory: Directory
    get() = this.layout.buildDirectory.dir("report").get()

val Project.linterReportingDirectory: Directory
    get() = this.reportingDirectory.dir("linter")

val Project.unitTestReportingDirectory: Directory
    get() = this.reportingDirectory.dir("test")

val Project.coverageReportingDirectory: Directory
    get() = this.reporting.baseDirectory.get().dir("coverage")
