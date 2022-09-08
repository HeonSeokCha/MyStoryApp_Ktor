package net.cheszx.repository

import net.cheszx.service.CreateUserParams
import net.cheszx.utils.BaseResponse

interface UserRepository {
    suspend fun registerUser(params: CreateUserParams): BaseResponse<Any>
    suspend fun loginUser(email: String, password: String): BaseResponse<Any>
}