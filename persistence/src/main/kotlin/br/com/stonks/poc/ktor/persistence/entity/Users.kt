package br.com.stonks.poc.ktor.persistence.entity

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object Users: Table() {
    val id: Column<Long> = long("id").autoIncrement().primaryKey()
    val name: Column<String> = varchar("name", 100)
    val email: Column<String> = varchar("email", 100)
    val phone: Column<String> = varchar("phone", 30)
    val password: Column<String> = varchar("password", 100)
    val active: Column<Boolean> = bool("active")
}
