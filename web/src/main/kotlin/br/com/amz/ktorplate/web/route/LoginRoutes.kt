package br.com.amz.ktorplate.web.route

import br.com.amz.ktorplate.web.controller.LoginController
import br.com.amz.ktorplate.web.dto.LoginRequest
import br.com.amz.ktorplate.web.dto.SignUpRequest
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.post
import org.koin.ktor.ext.inject

fun Routing.loginApis() {
    val loginController: LoginController by this.inject()

    post("/signup") {
        val request = call.receive<SignUpRequest>()
        call.respond(loginController.signUp(request))
    }

    post("/login") {
        val request = call.receive<LoginRequest>()
        call.respond(loginController.login(request))
    }
}
