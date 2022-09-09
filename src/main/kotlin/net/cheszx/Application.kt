package net.cheszx

import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import net.cheszx.db.DatabaseFactory
import net.cheszx.db.UserTable
import net.cheszx.plugins.*
import net.cheszx.repository.UserRepository
import net.cheszx.repository.UserRepositoryImpl
import net.cheszx.routes.authRoutes
import net.cheszx.security.configureSecurity
import net.cheszx.service.UserService
import net.cheszx.service.UserServiceImpl
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun main() {
    embeddedServer(Netty, port = 8080, host = "127.0.0.1") {
        DatabaseFactory.init()
        install(ContentNegotiation) {
            jackson()
        }
        configureSecurity()
        val service: UserService = UserServiceImpl()
        val repository:UserRepository =  UserRepositoryImpl(service)

        authRoutes(repository)

        routing {
            authenticate {
                get("/testurl"){
                    call.respond("workingFine")
                }
            }
        }
    }.start(wait = true)
}
