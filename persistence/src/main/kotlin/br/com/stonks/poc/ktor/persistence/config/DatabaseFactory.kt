package br.com.stonks.poc.ktor.persistence.config

import com.typesafe.config.ConfigFactory
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.config.HoconApplicationConfig
import io.ktor.util.KtorExperimentalAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction

@KtorExperimentalAPI
object DatabaseFactory {
    private val env = HoconApplicationConfig(ConfigFactory.load())
    private val dbUrl = env.property("db.jdbcUrl").getString()
    private val dbUser = env.property("db.dbUser").getString()
    private val dbPassword = env.property("db.dbPassword").getString()

    fun init() {
        Database.connect(dataSource())

        Flyway.configure()
            .dataSource(dbUrl, dbUser, dbPassword)
            .load()
            .migrate()
    }

    private fun dataSource() = HikariConfig().let {
        it.driverClassName = "org.postgresql.Driver"
        it.jdbcUrl = dbUrl
        it.username = dbUser
        it.password = dbPassword
        it.maximumPoolSize = 3
        it.isAutoCommit = false
        it.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        it.validate()

        HikariDataSource(it)
    }

    /**
     * In order to help make non-blocking queries, this function starts a coroutine for each
     * query that runs on a special thread pool "Dispatcher.IO", that is optimised for IO heavy operations.
     * */
    suspend fun <T> query(block: () -> T): T =
        withContext(Dispatchers.IO) {
            transaction { block() }
        }
}
