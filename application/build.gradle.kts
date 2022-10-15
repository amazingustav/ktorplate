val ktorVersion = findProperty("ktor.version")

plugins {
    application
}

dependencies {
    implementation(project(":usecase"))
    implementation(project(":persistence"))
    implementation(project(":entity"))
    implementation(project(":web"))
}

application {
    mainClass.set("br.com.amz.ktorplate.application.Application")

    applicationDefaultJvmArgs = listOf(
        "-server",
        "-XX:+UseNUMA",
        "-XX:+UseParallelGC",
        "-Duser.timezone=America/Sao_Paulo"
    )
}

tasks {
    jar {
        archiveBaseName.set("app")
        archiveVersion.set("")

        manifest {
            attributes(mapOf("Main-Class" to application.mainClassName))
        }

        from(Callable {
            configurations["runtimeClasspath"].map { if (it.isDirectory) it else zipTree(it) }
        })
    }
}
