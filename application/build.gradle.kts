plugins {
    application
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

dependencies {
    implementation(project(":usecase"))
    implementation(project(":persistence"))
    implementation(project(":entity"))
    implementation(project(":web"))
}

tasks.jar {
    archiveBaseName.set("app")
    archiveVersion.set("")
    duplicatesStrategy = DuplicatesStrategy.INCLUDE

    manifest {
        attributes(mapOf("Main-Class" to application.mainClass))
    }

    from(Callable {
        configurations["runtimeClasspath"].map { if (it.isDirectory) it else zipTree(it) }
    })
}
