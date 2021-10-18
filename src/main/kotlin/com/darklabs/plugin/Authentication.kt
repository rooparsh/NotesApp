package com.darklabs.plugin

import com.darklabs.repository.AuthRepository
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*

fun Application.configureAuthentication(authRepository: AuthRepository) {
    install(Authentication) {
        jwt {
            verifier(authRepository.verifyToken())
            realm = authRepository.getRealm()
            validate { jwtCredential ->
                authRepository.getJwtPrincipal(jwtCredential)
            }
        }
    }
}