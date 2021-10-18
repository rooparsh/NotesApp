package com.darklabs.route.router

import com.darklabs.repository.AuthRepository
import com.darklabs.route.auth.loginRoute
import com.darklabs.route.auth.registerRoute
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.authRouter(authRepository: AuthRepository) {

    registerRoute(authRepository)
    loginRoute(authRepository)

    authenticate {
        get("/me") {
            call.principal<JWTPrincipal>()?.let { jwtPrincipal ->
                val username = jwtPrincipal.payload.getClaim("username").asString()
                val userId = jwtPrincipal.payload.getClaim("userId").asInt()

                call.respondText("Hello, $username with id : $userId")
            }
        }
    }

}