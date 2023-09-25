val ktorVersion = findProperty("ktor.version").toString()
val koinVersion = findProperty("koin.version").toString()

dependencies {
    implementation(project(":usecase"))
    implementation(project(":entity"))

    implementation("io.ktor:ktor-server-auth-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-auth-jwt-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-netty-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-resources:$ktorVersion")
    implementation("io.ktor:ktor-server-swagger-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-core-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-status-pages-jvm:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:$ktorVersion")
    implementation("io.insert-koin:koin-ktor:$koinVersion")

    testImplementation("io.ktor:ktor-server-tests-jvm:$ktorVersion")
}
