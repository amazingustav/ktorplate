package br.com.amz.ktorplate.application

import br.com.amz.ktorplate.persistence.config.DatabaseFactory
import br.com.stonks.poc.ktor.persistence.config.repositoryModule
import br.com.stonks.poc.ktor.usecases.config.serviceModule
import br.com.stonks.poc.ktor.web.config.controllerModule
import br.com.stonks.poc.ktor.web.server.ServerBoot
import org.koin.core.context.startKoin

class Application {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            startKoin {
                modules(
                    listOf(
                        controllerModule,
                        repositoryModule,
                        serviceModule()
                    )
                )
            }

            _root_ide_package_.br.com.amz.ktorplate.persistence.config.DatabaseFactory.init()
            ServerBoot.boot(args)
        }
    }
}
