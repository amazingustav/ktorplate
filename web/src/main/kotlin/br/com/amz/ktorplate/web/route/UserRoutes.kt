package br.com.amz.ktorplate.web.route

import br.com.amz.ktorplate.web.controller.UserController
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.locations.Location
import io.ktor.locations.get
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.route
import org.koin.ktor.ext.inject

@KtorExperimentalLocationsAPI
fun Routing.userApis() {
    val userController: UserController by inject()

    authenticate {
        route("/users") {
            get { call.respond(userController.getUsers()) }

            get<UserParam> {
                call.respond(userController.getUserByEmail(this.context.parameters["userEmail"].toString()))
            }
        }
    }
}

@KtorExperimentalLocationsAPI
@Location("/{userEmail}")
data class UserParam(val userEmail: String)
