package br.com.stonks.poc.ktor.application

import br.com.stonks.poc.ktor.exception.PreConditionFailedException
import br.com.stonks.poc.ktor.exception.UnauthorizedException
import br.com.stonks.poc.ktor.usecases.config.JWTCredential
import br.com.stonks.poc.ktor.persistence.config.DatabaseFactory
import br.com.stonks.poc.ktor.persistence.config.repositoryModule
import br.com.stonks.poc.ktor.usecases.config.serviceModule
import br.com.stonks.poc.ktor.web.config.controllerModule
import br.com.stonks.poc.ktor.web.route.loginApis
import br.com.stonks.poc.ktor.web.route.userApis
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.auth.UserIdPrincipal
import io.ktor.auth.jwt.JWTPrincipal
import io.ktor.auth.jwt.jwt
import io.ktor.features.ContentNegotiation
import io.ktor.features.NotFoundException
import io.ktor.features.StatusPages
import io.ktor.gson.gson
import io.ktor.http.HttpStatusCode
import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.locations.Locations
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.netty.EngineMain
import io.ktor.util.KtorExperimentalAPI
import org.koin.ktor.ext.installKoin

class Application {
    companion object{
        @JvmStatic
        fun main(args: Array<String>) = EngineMain.main(args)
    }
}

@KtorExperimentalAPI
@KtorExperimentalLocationsAPI
fun Application.module() {
    installKoin(
        listOf(
            controllerModule,
            repositoryModule,
            serviceModule(this.environment)
        )
    )

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

    DatabaseFactory.init()

    routing {
        get("/health") { call.respond(mapOf("status" to "OK")) }

        loginApis()
        userApis()
    }
}
