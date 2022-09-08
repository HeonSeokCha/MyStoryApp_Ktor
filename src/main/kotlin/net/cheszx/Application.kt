package net.cheszx

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import net.cheszx.db.DatabaseFactory
import net.cheszx.db.UserTable
import net.cheszx.plugins.*
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun main() {
    embeddedServer(Netty, port = 8080, host = "127.0.0.1") {
        DatabaseFactory.init()
        transaction {
            SchemaUtils.create(UserTable)
        }
        configureRouting()
    }.start(wait = true)
}
