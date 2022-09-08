package net.cheszx.model

data class User(
    val id: Int,
    val fullName: String,
    val avatar: String,
    val email: String,
    var authToken: String? = null,
    val createDate: String
)
