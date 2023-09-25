package br.com.amz.ktorplate.web.route

import br.com.amz.ktorplate.web.controller.UserController
import io.ktor.resources.Resource
import io.ktor.server.application.call
import io.ktor.server.auth.authenticate
import io.ktor.server.resources.get
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import kotlinx.serialization.Serializable
import org.koin.ktor.ext.inject

fun Routing.userApis() {
    val userController: UserController by inject()

    authenticate {
        route("/users") {
            get { call.respond(userController.getUsers()) }

            get<UserParam> {
                call.respond(userController.getUserByEmail(this.context.parameters["email"]!!))
            }
        }
    }
}

@Serializable
@Resource("/{email}")
data class UserParam(val email: String)