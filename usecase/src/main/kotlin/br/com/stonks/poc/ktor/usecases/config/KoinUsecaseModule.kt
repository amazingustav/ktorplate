package br.com.stonks.poc.ktor.usecases.config

import br.com.stonks.poc.ktor.usecases.service.LoginService
import br.com.stonks.poc.ktor.usecases.service.UserService
import com.typesafe.config.ConfigFactory
import org.koin.dsl.module

fun serviceModule() = module {
    single { ConfigFactory.load() }
    single { LoginService(get(), get()) }
    single { UserService(get()) }
}
