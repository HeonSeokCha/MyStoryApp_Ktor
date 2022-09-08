package net.cheszx.db

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {

    fun init() {
        Database.connect(hikari())
    }

    private fun hikari(): HikariDataSource {
        val config = HikariConfig().apply {
            this.driverClassName = "org.postgresql.Driver"
            this.username = "postgres"
            this.password = System.getenv("postgres_pw")
            this.jdbcUrl = "jdbc:postgresql:mystoryapp"
            this.maximumPoolSize = 3
            this.isAutoCommit = false
            this.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
            this.validate()
        }
        return HikariDataSource(config)
    }

    suspend fun <T> dbQuery(block: () -> T): T = withContext(Dispatchers.IO) {
        transaction { block() }
    }
}