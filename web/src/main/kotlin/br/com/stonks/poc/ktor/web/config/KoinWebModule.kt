package br.com.stonks.poc.ktor.web.config

import br.com.stonks.poc.ktor.web.controller.LoginController
import br.com.stonks.poc.ktor.web.controller.UserController
import io.ktor.util.KtorExperimentalAPI
import org.koin.dsl.module.module

@KtorExperimentalAPI
val controllerModule = module {
    single { LoginController(get()) }
    single { UserController(get()) }
}
