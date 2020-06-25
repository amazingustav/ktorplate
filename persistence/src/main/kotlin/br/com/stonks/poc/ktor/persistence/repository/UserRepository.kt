package br.com.stonks.poc.ktor.persistence.repository

import br.com.stonks.poc.ktor.persistence.config.DatabaseFactory
import br.com.stonks.poc.ktor.persistence.entity.Users
import br.com.stonks.poc.ktor.usecases.adapter.UserAdapter
import br.com.stonks.poc.ktor.persistence.utils.toUser
import br.com.stonks.poc.ktor.user.User
import io.ktor.util.KtorExperimentalAPI
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.mindrot.jbcrypt.BCrypt

@KtorExperimentalAPI
class UserRepository: UserAdapter {
    override suspend fun findAll() = DatabaseFactory.query { Users.selectAll().map { it.toUser() } }

    override suspend fun findByEmail(email: String) = DatabaseFactory.query {
        Users.select { (Users.email eq email) }
            .mapNotNull { it.toUser() }
            .singleOrNull()
    }

    override suspend fun save(user: User) = DatabaseFactory.query {
        Users.insert {
            it[name] = user.name ?: ""
            it[email] = user.email
            it[phone] = user.phone ?: ""
            it[password] = BCrypt.hashpw(user.password, BCrypt.gensalt())
            it[active] = user.active
        }[Users.id]
    }
}
