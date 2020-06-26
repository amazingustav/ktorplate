package br.com.stonks.poc.ktor.web.server

import br.com.stonks.poc.ktor.exception.NotFoundException
import br.com.stonks.poc.ktor.exception.PreConditionFailedException
import br.com.stonks.poc.ktor.exception.UnauthorizedException
import br.com.stonks.poc.ktor.usecases.config.JWTCredential
import br.com.stonks.poc.ktor.web.route.loginApis
import br.com.stonks.poc.ktor.web.route.userApis
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.auth.jwt.JWTPrincipal
import io.ktor.auth.jwt.jwt
import io.ktor.features.ContentNegotiation
import io.ktor.features.StatusPages
import io.ktor.gson.gson
import io.ktor.http.HttpStatusCode
import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.locations.Locations
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.util.KtorExperimentalAPI

@KtorExperimentalLocationsAPI
@KtorExperimentalAPI
fun Application.module() {
    install(Locations)
    install(ContentNegotiation) {
        gson { setPrettyPrinting() }
    }

    install(StatusPages) {
        exception<UnauthorizedException> { call.respond(HttpStatusCode.Unauthorized) }
        exception<NotFoundException> { call.respond(HttpStatusCode.NotFound) }
        exception<PreConditionFailedException> { call.respond(HttpStatusCode.PreconditionFailed) }
    }

    install(Authentication) {
        jwt {
            JWTCredential(environment.config.property("jwt.secret").getString()).also { verifier(it.verifier) }
            validate { JWTPrincipal(it.payload) }
        }
    }

    routing {
        get("/health") { call.respond(mapOf("status" to "OK")) }

        loginApis()
        userApis()
    }
}