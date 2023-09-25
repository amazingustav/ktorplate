package br.com.amz.ktorplate.user

import java.util.UUID

data class User(
    val id: UUID? = null,
    val name: String? = null,
    val email: String,
    val phone: String? = null,
    val password: String,
    val active: Boolean = true
)
