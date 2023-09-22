val koinVersion = "2.1.6"

plugins {
    kotlin("jvm") version "1.3.72"
}

repositories {
    mavenCentral()
}

subprojects {
    version = "1.0.0"
    group = "br.com.amz"

    apply(plugin = "kotlin")

    dependencies {
        implementation(kotlin("stdlib"))
        implementation(kotlin("reflect"))

        implementation("com.typesafe:config:1.4.0")

        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.7")
        // koin dependencies
        implementation("org.koin:koin-core:$koinVersion")
        implementation("org.koin:koin-core-ext:$koinVersion")

        implementation("org.mindrot:jbcrypt:0.4")
        implementation("ch.qos.logback:logback-classic:1.2.1")

        // Test suit
        testImplementation("junit:junit:4.12")
    }

    tasks.compileKotlin {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjvm-default=enable")
            allWarningsAsErrors = true
            jvmTarget = "17"
        }
    }

    tasks.compileTestKotlin {
        kotlinOptions {
            jvmTarget = "17"
        }
    }

    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
    }
}
