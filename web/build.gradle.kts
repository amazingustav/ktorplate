val ktorVersion = findProperty("ktor.version").toString()
val koinVersion = findProperty("koin.version").toString()

dependencies {
    implementation(project(":usecase"))
    implementation(project(":entity"))

    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-gson:$ktorVersion")
    implementation("io.ktor:ktor-locations:$ktorVersion")
    implementation("io.ktor:ktor-auth-jwt:$ktorVersion")
    implementation("io.insert-koin:koin-ktor:$koinVersion")

    testImplementation("io.ktor:ktor-server-tests:$ktorVersion")
}
