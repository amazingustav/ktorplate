package br.com.amz.ktorplate.usecases.service

import br.com.amz.ktorplate.usecases.adapter.UserAdapter

class UserService(private val userAdapter: UserAdapter) {
    suspend fun findAll() = userAdapter.findAll()

    suspend fun findByEmail(email: String) = userAdapter.findByEmail(email)
}
