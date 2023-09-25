package br.com.amz.ktorplate.usecases.config

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import java.util.Date
import java.util.UUID

class JWTCredential(secret: String) {
    private val algorithm = Algorithm.HMAC256(secret)
    val verifier = JWT.require(algorithm).build()!!

    fun sign(userId: UUID) = JWT.create()
        .withClaim("userId", userId.toString())
        .withExpiresAt(getExpiration())
        .sign(algorithm)!!

    private fun getExpiration() = Date(System.currentTimeMillis() + ONE_HOUR_IN_MILLIS)

    companion object {
        private const val ONE_HOUR_IN_MILLIS = 3_600_000 * 1
    }
}
