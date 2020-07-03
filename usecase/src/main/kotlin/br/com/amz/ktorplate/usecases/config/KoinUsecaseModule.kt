package br.com.amz.ktorplate.usecases.config

import br.com.amz.ktorplate.usecases.service.LoginService
import br.com.amz.ktorplate.usecases.service.UserService
import com.typesafe.config.ConfigFactory
import org.koin.dsl.module

fun serviceModule() = module {
    single { ConfigFactory.load() }
    single { LoginService(get(), get()) }
    single { UserService(get()) }
}
