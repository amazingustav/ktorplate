package br.com.amz.ktorplate.usecases.adapter

import br.com.amz.ktorplate.user.User

interface UserAdapter {
    suspend fun findAll(): List<User>

    suspend fun findByEmail(email: String): User?

    suspend fun save(user: User): Long
}
