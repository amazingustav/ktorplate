package br.com.stonks.poc.ktor.persistence.config

import br.com.stonks.poc.ktor.persistence.repository.UserRepository
import br.com.stonks.poc.ktor.usecases.adapter.UserAdapter
import io.ktor.util.KtorExperimentalAPI
import org.koin.dsl.module.module

@KtorExperimentalAPI
val repositoryModule = module {
    single<UserAdapter> { UserRepository() }
}
