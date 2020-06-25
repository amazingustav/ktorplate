package br.com.stonks.poc.ktor.usecases.service

import br.com.stonks.poc.ktor.exception.UnauthorizedException
import br.com.stonks.poc.ktor.usecases.adapter.UserAdapter
import br.com.stonks.poc.ktor.usecases.config.JWTCredential
import br.com.stonks.poc.ktor.user.User
import io.ktor.application.ApplicationEnvironment
import io.ktor.features.NotFoundException
import io.ktor.util.KtorExperimentalAPI
import org.mindrot.jbcrypt.BCrypt

@KtorExperimentalAPI
class LoginService(
    environment: ApplicationEnvironment,
    private val userAdapter: UserAdapter
) {

    private val credential = JWTCredential(environment.config.property("jwt.secret").getString())

    suspend fun register(user: User): Long = userAdapter.save(user)

    @Throws(UnauthorizedException::class)
    suspend fun login(user: User): String {
        val persistedUser = userAdapter.findByEmail(user.email) ?: throw NotFoundException()

        if (!BCrypt.checkpw(user.password, persistedUser.password)) throw UnauthorizedException("Invalid credentials")

        return credential.sign(persistedUser.id!!)
    }
}
