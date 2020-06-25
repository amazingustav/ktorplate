plugins {
    id("org.flywaydb.flyway") version "6.4.0"
}

dependencies {
    implementation("com.zaxxer:HikariCP:3.4.3")
    implementation("org.postgresql:postgresql:42.2.2")
    implementation("org.jetbrains.exposed:exposed:0.17.7")
    implementation("org.flywaydb:flyway-core:6.4.0")

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
