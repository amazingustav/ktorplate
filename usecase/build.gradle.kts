val jwtVersion = findProperty("jwt.version")

dependencies {
    implementation(project(":entity"))

    implementation("com.auth0:java-jwt:$jwtVersion")
}
