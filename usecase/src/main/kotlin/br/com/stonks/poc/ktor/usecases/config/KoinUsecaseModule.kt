package br.com.stonks.poc.ktor.usecases.config

import br.com.stonks.poc.ktor.usecases.service.LoginService
import br.com.stonks.poc.ktor.usecases.service.UserService
import io.ktor.application.ApplicationEnvironment
import io.ktor.util.KtorExperimentalAPI
import org.koin.dsl.module.module

@KtorExperimentalAPI
fun serviceModule(applicationEnvironment: ApplicationEnvironment) = module {
    single { applicationEnvironment }
    single { LoginService(applicationEnvironment, get()) }
    single { UserService(get()) }
}
