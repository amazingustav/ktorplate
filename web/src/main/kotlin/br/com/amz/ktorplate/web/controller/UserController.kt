package br.com.amz.ktorplate.web.controller

import br.com.amz.ktorplate.exception.PreConditionFailedException
import br.com.amz.ktorplate.usecases.service.UserService
import br.com.amz.ktorplate.user.User
import io.ktor.server.plugins.NotFoundException

class UserController(private val userService: UserService) {

    suspend fun getUsers() = userService.findAll()

    suspend fun getUserByEmail(email: String): User {
        val user = userService.findByEmail(email) ?: throw NotFoundException()

        return if (!user.active) throw PreConditionFailedException("User is not active") else user
    }
}
