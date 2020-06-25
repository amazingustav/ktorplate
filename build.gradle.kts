import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val ktorVersion = "1.3.2"
val koinVersion = "1.0.1"
val kotlinVersion = "1.3.70"

plugins {
    kotlin("jvm") version "1.3.72"
}

repositories {
    jcenter()
    mavenCentral()
}

subprojects {
    version = "0.0.1"
    group = "br.com.stonks.poc"

    apply(plugin = "kotlin")

    repositories {
        jcenter()
    }

    dependencies {
        implementation(kotlin("stdlib"))
        implementation(kotlin("reflect"))

        // Ktor dependencies
        implementation("io.ktor:ktor-server-core:$ktorVersion")
        implementation("io.ktor:ktor-locations:$ktorVersion")
        implementation("io.ktor:ktor-auth-jwt:$ktorVersion")
        implementation("org.mindrot:jbcrypt:0.4")
        implementation("org.koin:koin-ktor:$koinVersion")

        implementation("ch.qos.logback:logback-classic:1.2.1")

        // Test suit
        testImplementation("junit:junit:4.12")
    }

    tasks.compileKotlin {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjvm-default=enable")
            allWarningsAsErrors = true
            jvmTarget = "11"
        }
    }

    tasks.compileTestKotlin {
        kotlinOptions {
            jvmTarget = "11"
        }
    }

    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
    }
}
