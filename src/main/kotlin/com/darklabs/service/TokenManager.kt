package com.darklabs.service

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.darklabs.model.User
import io.ktor.config.*
import java.util.*

class TokenManager(config: ApplicationConfig) {

    private val audience = config.propertyOrNull("jwt.audience")?.getString().orEmpty()
    private val secret = config.propertyOrNull("jwt.secret")?.getString().orEmpty()
    private val issuer = config.propertyOrNull("jwt.issuer")?.getString().orEmpty()
    val realm = config.propertyOrNull("jwt.realm")?.getString().orEmpty()
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