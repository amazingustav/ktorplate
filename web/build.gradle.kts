val ktorVersion = "1.3.2"
val koinVersion = "2.1.6"

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
