package br.com.stonks.poc.ktor.persistence.utils

import br.com.stonks.poc.ktor.persistence.entity.Users
import br.com.stonks.poc.ktor.user.User
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toUser() = User(
    id = this[Users.id],
    name = this[Users.name],
    email = this[Users.email],
    phone = this[Users.phone],
    password = this[Users.password],
    active = this[Users.active]
)
