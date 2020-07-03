package br.com.amz.ktorplate.application

import br.com.amz.ktorplate.persistence.config.DatabaseFactory
import br.com.amz.ktorplate.persistence.config.repositoryModule
import br.com.amz.ktorplate.usecases.config.serviceModule
import br.com.amz.ktorplate.web.config.controllerModule
import br.com.amz.ktorplate.web.server.ServerBoot
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

            DatabaseFactory.init()
            ServerBoot.boot(args)
        }
    }
}
