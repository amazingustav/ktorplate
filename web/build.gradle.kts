val ktorVersion = findProperty("ktor.version")
val koinVersion = findProperty("koin.version")

dependencies {
    implementation(project(":usecase"))
    implementation(project(":entity"))

    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-gson:$ktorVersion")
    implementation("io.ktor:ktor-locations:$ktorVersion")
    implementation("io.ktor:ktor-auth-jwt:$ktorVersion")
    implementation("org.koin:koin-ktor:$koinVersion")

    testImplementation("io.ktor:ktor-server-tests:$ktorVersion")
}
