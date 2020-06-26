package br.com.stonks.poc.ktor.web.controller

import br.com.stonks.poc.ktor.exception.PreConditionFailedException
import br.com.stonks.poc.ktor.usecases.service.UserService
import br.com.stonks.poc.ktor.user.User
import io.ktor.features.NotFoundException
import io.ktor.util.KtorExperimentalAPI

class UserController(private val userService: UserService) {

    suspend fun getUsers() = userService.findAll()

    suspend fun getUserByEmail(email: String): User {
        val user = userService.findByEmail(email) ?: throw NotFoundException()

        return if (!user.active) throw PreConditionFailedException("User is not active") else user
    }
}
