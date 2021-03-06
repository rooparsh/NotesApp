package com.darklabs.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.mindrot.jbcrypt.BCrypt

@Serializable
data class User(
    @SerialName("id") val id: Int? = null,
    @SerialName("username") val username: String,
    @SerialName("password") val password: String
) {
    fun hashedPassword(): String = BCrypt.hashpw(password, BCrypt.gensalt())

    fun isValidCred() = username.length >= 3 && password.length >= 6
}
