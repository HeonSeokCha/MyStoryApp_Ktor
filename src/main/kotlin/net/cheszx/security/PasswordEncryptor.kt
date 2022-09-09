package net.cheszx.security

import io.ktor.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

val SECRET_KEY = "1234556"
val ALGORITHM = "HmacSHA1"

private val HASH_KEY = hex(SECRET_KEY)
private val HMAC_KEY = SecretKeySpec(HASH_KEY, ALGORITHM)

fun hash(passWord: String): String {
    val hmac = Mac.getInstance(ALGORITHM)
    hmac.init(HMAC_KEY)
    return hex(hmac.doFinal(passWord.toByteArray((Charsets.UTF_8))))
}