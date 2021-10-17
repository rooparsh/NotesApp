package com.darklabs.service

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.darklabs.model.User
import io.ktor.config.*
import java.util.*

class TokenManager(config: HoconApplicationConfig) {

    private val audience = config.propertyOrNull("jwt.audience")?.getString() ?: "http://0.0.0.0:8080/me"
    private val secret = config.propertyOrNull("jwt.secret")?.getString() ?: "secret111"
    private val issuer = config.propertyOrNull("jwt.issuer")?.getString() ?: "http://0.0.0.0:8080/"
    val realm = config.propertyOrNull("jwt.realm")?.getString() ?: "Access to 'me'"
    private val expDate = System.currentTimeMillis() + 600_000

    fun generateJwtToken(user: User): String? {
        return JWT.create()
            .withAudience(audience)
            .withIssuer(issuer)
            .withClaim("username", user.username)
            .withClaim("userId", user.id)
            .withExpiresAt(Date(expDate))
            .sign(Algorithm.HMAC256(secret))
    }

    fun verifyJwtToken(): JWTVerifier {
        return JWT.require(Algorithm.HMAC256(secret))
            .withAudience(audience)
            .withIssuer(issuer)
            .build()
    }
}