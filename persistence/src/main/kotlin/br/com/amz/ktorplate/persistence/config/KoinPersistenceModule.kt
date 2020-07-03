package br.com.amz.ktorplate.persistence.config

import br.com.amz.ktorplate.persistence.repository.UserRepository
import br.com.amz.ktorplate.usecases.adapter.UserAdapter
import org.koin.dsl.module

val repositoryModule = module {
    single<UserAdapter> { UserRepository() }
}
