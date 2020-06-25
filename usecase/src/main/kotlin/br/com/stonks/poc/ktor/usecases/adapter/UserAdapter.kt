package br.com.stonks.poc.ktor.usecases.adapter

import br.com.stonks.poc.ktor.user.User

interface UserAdapter {
    suspend fun findAll(): List<User>

    suspend fun findByEmail(email: String): User?

    suspend fun save(user: User): Long
}
