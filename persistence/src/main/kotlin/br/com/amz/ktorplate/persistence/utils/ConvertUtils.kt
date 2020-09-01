package br.com.amz.ktorplate.persistence.utils

import br.com.amz.ktorplate.persistence.entity.Users
import br.com.amz.ktorplate.user.User
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toUser() = User(
    id = this[Users.id],
    name = this[Users.name],
    email = this[Users.email],
    phone = this[Users.phone],
    password = this[Users.password],
    active = this[Users.active]
)
