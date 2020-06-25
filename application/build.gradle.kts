val ktorVersion = "1.3.2"

plugins {
    application
}

application {
    mainClassName = "br.com.stonks.poc.ktor.application.Application"

    applicationDefaultJvmArgs = listOf(
        "-server",
        "-XX:+UseNUMA",
        "-XX:+UseParallelGC",
        "-Duser.timezone=America/Sao_Paulo"
    )
}

dependencies {
    implementation(project(":usecase"))
    implementation(project(":persistence"))
    implementation(project(":entity"))
    implementation(project(":web"))

    implementation("io.ktor:ktor-gson:$ktorVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    testImplementation("io.ktor:ktor-server-tests:$ktorVersion")
}

tasks.jar {
    archiveBaseName.set("app")
    archiveVersion.set("")

    manifest {
        attributes(mapOf("Main-Class" to application.mainClassName))
    }

    from(Callable {
        configurations["runtimeClasspath"].map { if (it.isDirectory) it else zipTree(it) }
    })
}
