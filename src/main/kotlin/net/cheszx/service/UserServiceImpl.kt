package net.cheszx.service

import net.cheszx.db.DatabaseFactory.dbQuery
import net.cheszx.db.UserTable
import net.cheszx.model.User
import net.cheszx.security.hash
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.statements.InsertStatement

class UserServiceImpl : UserService {
    override suspend fun registerUser(params: CreateUserParams): User? {
        var statement: InsertStatement<Number>? = null
        dbQuery {
            statement = UserTable.insert {
                it[email] = params.email
                it[password] = hash(params.password)
                it[fullName] = params.fullName
                it[avatar] = params.avatar
            }
        }
        return rowToUser(statement?.resultedValues?.get(0))
    }

    override suspend fun findUserByEmail(email: String): User? {
        return dbQuery {
            UserTable.select {
                UserTable.email.eq(email)
            }.map {
                rowToUser(it)
            }.singleOrNull()
        }
    }

    private fun rowToUser(row: ResultRow?): User? {
        return if (row == null) null
        else User(
            id = row[UserTable.id],
            fullName = row[UserTable.fullName],
            avatar = row[UserTable.avatar],
            email = row[UserTable.email],
            createDate = row[UserTable.createDate].toString(),
        )
    }
}