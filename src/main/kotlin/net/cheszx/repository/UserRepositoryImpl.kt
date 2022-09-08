package net.cheszx.repository

import net.cheszx.service.CreateUserParams
import net.cheszx.service.UserService
import net.cheszx.utils.BaseResponse

class UserRepositoryImpl(
    private val userService: UserService
) : UserRepository {
    override suspend fun registerUser(params: CreateUserParams): BaseResponse<Any> {
        return if (isEmailExist(params.email)) {
            BaseResponse.ErrorResponse(message = "Email already registered")
        } else {
            val user = userService.registerUser(params)
            if (user != null) {
                BaseResponse.SuccessResponse(data = user)
            } else {
                BaseResponse.ErrorResponse()
            }
        }
    }

    override suspend fun loginUser(email: String, password: String): BaseResponse<Any> {
        TODO("Not yet implemented")
    }

    private suspend fun isEmailExist(email: String): Boolean {
        return userService.findUserByEmail(email) != null
    }
}