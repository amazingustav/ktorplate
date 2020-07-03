package br.com.amz.ktorplate.web.dto

data class SignUpRequest(
    val name: String,
    val email: String,
    val phone: String,
    val password: String
)

data class LoginRequest(val email: String, val password: String)
