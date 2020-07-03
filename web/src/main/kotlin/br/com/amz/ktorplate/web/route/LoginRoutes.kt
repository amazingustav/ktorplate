package br.com.amz.ktorplate.web.route

import br.com.amz.ktorplate.web.controller.LoginController
import br.com.amz.ktorplate.web.dto.LoginRequest
import br.com.amz.ktorplate.web.dto.SignUpRequest
import io.ktor.application.call
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.post
import io.ktor.util.KtorExperimentalAPI
import org.koin.ktor.ext.inject

@KtorExperimentalAPI
fun Routing.loginApis() {
    val loginController: LoginController by inject()

    post("/signup") {
        val request = call.receive<SignUpRequest>()
        call.respond(loginController.signUp(request))
    }

    post("/login") {
        val request = call.receive<LoginRequest>()
        call.respond(loginController.login(request))
    }
}
