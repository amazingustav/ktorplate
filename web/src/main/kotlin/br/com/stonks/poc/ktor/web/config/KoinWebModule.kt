package br.com.stonks.poc.ktor.web.config

import br.com.stonks.poc.ktor.web.controller.LoginController
import br.com.stonks.poc.ktor.web.controller.UserController
import org.koin.dsl.module

val controllerModule = module {
    single { LoginController(get()) }
    single { UserController(get()) }
}
