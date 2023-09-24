val configVersion = findProperty("config.version").toString()
val coroutinesVersion = findProperty("coroutines.version").toString()
val jbcryptVersion = findProperty("jbcrypt.version").toString()
val logbackVersion = findProperty("logback.version").toString()
val junitVersion = findProperty("junit.version").toString()
val koinVersion = findProperty("koin.version").toString()
val jvmTargetVersion = findProperty("jvm.version").toString()

plugins {
    val kotlinVersion = "1.3.72"
    kotlin("jvm") version kotlinVersion
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

        implementation("com.typesafe:config:$configVersion")
        implementation("org.mindrot:jbcrypt:$jbcryptVersion")
        implementation("ch.qos.logback:logback-classic:$logbackVersion")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")

        implementation("org.koin:koin-core:$koinVersion")
        implementation("org.koin:koin-core-ext:$koinVersion")

        testImplementation("junit:junit:$junitVersion")
    }

    tasks.compileKotlin {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjvm-default=enable")
            allWarningsAsErrors = true
            jvmTarget = jvmTargetVersion
        }
    }

    tasks.compileTestKotlin {
        kotlinOptions {
            jvmTarget = jvmTargetVersion
        }
    }

    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
    }
}
