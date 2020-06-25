package br.com.stonks.poc.ktor.exception

class UnauthorizedException(override val message: String = ""): Throwable(message)

class PreConditionFailedException(override val message: String = ""): Throwable(message)

class NotFoundException(override val message: String = ""): Throwable(message)
