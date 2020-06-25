package br.com.stonks.poc.ktor.user

data class User(
    val id: Long? = null,
    val name: String? = null,
    val email: String,
    val phone: String? = null,
    val password: String,
    val active: Boolean = true
)
