package br.com.amz.ktorplate.web.server

import br.com.amz.ktorplate.exception.NotFoundException
import br.com.amz.ktorplate.exception.PreConditionFailedException
import br.com.amz.ktorplate.exception.UnauthorizedException
import br.com.amz.ktorplate.usecases.config.JWTCredential
import br.com.amz.ktorplate.web.route.loginApis
import br.com.amz.ktorplate.web.route.userApis
import com.typesafe.config.ConfigFactory
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.application.install
import io.ktor.server.auth.authentication
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.jwt.jwt
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.plugins.swagger.swaggerUI
import io.ktor.server.resources.Resources
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing

private val env = ConfigFactory.load()

fun Application.module() {
    install(Resources)

    install(ContentNegotiation) {
        json()
    }

    install(StatusPages) {
        exception<UnauthorizedException> { call, cause ->
            call.respondText(text = "401: $cause", status = HttpStatusCode.Unauthorized)
        }

        exception<NotFoundException> { call, cause ->
            call.respondText(text = "404: $cause", status = HttpStatusCode.NotFound)
        }

        exception<PreConditionFailedException> { call, cause ->
            call.respondText(text = "412: $cause", status = HttpStatusCode.PreconditionFailed)
        }
    }

    configureAuthentication()
    configureRouting()
}

fun Application.configureRouting() {
    routing {
        get("/health") { call.respond(mapOf("status" to "OK")) }

        swaggerUI(path = "/swagger")

        loginApis()
        userApis()
    }
}

fun Application.configureAuthentication() {
    authentication {
        jwt {
            val jwtSecret = env.getString("jwt.secret")

            verifier(JWTCredential(jwtSecret).verifier)
            validate { JWTPrincipal(it.payload) }
        }
    }
}
