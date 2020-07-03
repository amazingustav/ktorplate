package br.com.amz.ktorplate.web.config

import br.com.amz.ktorplate.web.controller.LoginController
import br.com.amz.ktorplate.web.controller.UserController
import org.koin.dsl.module

val controllerModule = module {
    single { LoginController(get()) }
    single { UserController(get()) }
}
