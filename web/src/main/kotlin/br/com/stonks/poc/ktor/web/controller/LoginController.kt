package br.com.stonks.poc.ktor.web.controller

import br.com.stonks.poc.ktor.web.dto.LoginRequest
import br.com.stonks.poc.ktor.web.dto.SignUpRequest
import br.com.stonks.poc.ktor.usecases.service.LoginService
import br.com.stonks.poc.ktor.user.User
import io.ktor.http.HttpStatusCode
import io.ktor.util.KtorExperimentalAPI

@KtorExperimentalAPI
class LoginController(private val loginService: LoginService) {

    suspend fun signUp(request: SignUpRequest) = loginService.register(
        User(
            name = request.name,
            email = request.email,
            phone = request.phone,
            password = request.password
        )
    ).let { HttpStatusCode.Created }

    suspend fun login(request: LoginRequest) = loginService.login(
        User(email = request.email, password = request.password)
    ).let { mapOf("token" to it) }
}
