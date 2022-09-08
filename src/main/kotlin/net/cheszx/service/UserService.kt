package net.cheszx.service

import net.cheszx.model.User

interface UserService {
    suspend fun registerUser(params: CreateUserParams): User?
    suspend fun findUserByEmail(email: String): User?
}