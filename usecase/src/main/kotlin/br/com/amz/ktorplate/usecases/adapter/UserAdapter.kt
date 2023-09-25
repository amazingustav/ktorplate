package br.com.amz.ktorplate.usecases.adapter

import br.com.amz.ktorplate.user.User
import java.util.UUID

interface UserAdapter {
    suspend fun findAll(): List<User>

    suspend fun findByEmail(email: String): User?

    suspend fun save(user: User): UUID
}
