val hikariVersion = findProperty("hikari.version")
val postgresqlVersion = findProperty("postgresql.version")
val exposedVersion = findProperty("exposed.version")
val flywayVersion = findProperty("flyway.version")

plugins {
    val flywayVersion = "6.4.0"
    id("org.flywaydb.flyway") version flywayVersion
}

dependencies {
    implementation("com.zaxxer:HikariCP:$hikariVersion")
    implementation("org.postgresql:postgresql:$postgresqlVersion")
    implementation("org.jetbrains.exposed:exposed:$exposedVersion")
    implementation("org.flywaydb:flyway-core:$flywayVersion")

    implementation(project(":usecase"))
    implementation(project(":entity"))
}

flyway {
    url = System.getenv("DB_URL")
    user = System.getenv("DB_USER")
    password = System.getenv("DB_PASSWORD")
    baselineOnMigrate = true
    locations = arrayOf("classpath:db/migration")
}
