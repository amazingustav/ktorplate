package br.com.amz.ktorplate.persistence.repository

import br.com.amz.ktorplate.persistence.config.DatabaseFactory.query
import br.com.amz.ktorplate.persistence.entity.Users
import br.com.amz.ktorplate.persistence.utils.toUser
import br.com.amz.ktorplate.usecases.adapter.UserAdapter
import br.com.amz.ktorplate.user.User
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.mindrot.jbcrypt.BCrypt

class UserRepository : UserAdapter {
    override suspend fun findAll() = query { Users.selectAll().map { it.toUser() } }

    override suspend fun findByEmail(email: String) = query {
        Users.select { (Users.email eq email) }
            .mapNotNull { it.toUser() }
            .singleOrNull()
    }

    override suspend fun save(user: User) = query {
        Users.insert {
            it[name] = user.name ?: ""
            it[email] = user.email
            it[phone] = user.phone ?: ""
            it[password] = BCrypt.hashpw(user.password, BCrypt.gensalt())
            it[active] = user.active
        }[Users.id]
    }
}
