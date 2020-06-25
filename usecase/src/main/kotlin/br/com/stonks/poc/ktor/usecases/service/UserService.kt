package br.com.stonks.poc.ktor.usecases.service

import br.com.stonks.poc.ktor.usecases.adapter.UserAdapter
import io.ktor.util.KtorExperimentalAPI

@KtorExperimentalAPI
class UserService(private val userAdapter: UserAdapter) {
    suspend fun findAll() = userAdapter.findAll()

    suspend fun findByEmail(email: String) = userAdapter.findByEmail(email)
}
