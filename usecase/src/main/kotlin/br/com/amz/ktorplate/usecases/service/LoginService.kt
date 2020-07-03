package br.com.amz.ktorplate.usecases.service

import br.com.amz.ktorplate.exception.NotFoundException
import br.com.amz.ktorplate.exception.UnauthorizedException
import br.com.amz.ktorplate.usecases.adapter.UserAdapter
import br.com.amz.ktorplate.usecases.config.JWTCredential
import br.com.amz.ktorplate.user.User
import com.typesafe.config.Config
import org.mindrot.jbcrypt.BCrypt

class LoginService(
    config: Config,
    private val userAdapter: UserAdapter
) {

    private val credential = JWTCredential(config.getString("jwt.secret"))

    suspend fun register(user: User): Long = userAdapter.save(user)

    @Throws(UnauthorizedException::class)
    suspend fun login(user: User): String {
        val persistedUser = userAdapter.findByEmail(user.email) ?: throw NotFoundException()

        if (!BCrypt.checkpw(user.password, persistedUser.password)) throw UnauthorizedException(
            "Invalid credentials"
        )

        return credential.sign(persistedUser.id!!)
    }
}
