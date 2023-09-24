val auth0Version = findProperty("auth0.version").toString()

dependencies {
    implementation(project(":entity"))

    implementation("com.auth0:java-jwt:$auth0Version")
}
