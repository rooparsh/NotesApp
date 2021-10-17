package com.darklabs.plugin

import com.darklabs.service.TokenManager
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*

fun Application.configureAuthentication(tokenManager: TokenManager) {
    install(Authentication) {
        jwt {
            verifier(tokenManager.verifyJwtToken())
            realm = tokenManager.realm
            validate { jwtCredential ->
                if (jwtCredential.payload.getClaim("username").asString().isNotEmpty()) {
                    JWTPrincipal(jwtCredential.payload)
                } else {
                    null
                }
            }
        }
    }
}