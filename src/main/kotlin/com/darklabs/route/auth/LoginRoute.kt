package com.darklabs.route.auth

import com.darklabs.model.CommonResponse
import com.darklabs.model.User
import com.darklabs.repository.AuthRepository
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.loginRoute(authRepository: AuthRepository) {

    post("/login") {
        val userRequest = call.receive<User>()

        if (userRequest.isValidCred()) {
            call.respond(HttpStatusCode.BadRequest, CommonResponse(data = null, message = "Check validation"))
            return@post
        }

        val user = authRepository.checkUserExists(userRequest)

        if (user == null) {
            call.respond(HttpStatusCode.BadRequest, CommonResponse(data = null, message = "Invalid username/password"))
            return@post
        }

        val isPasswordMatch = authRepository.validatePassword(userRequest.password, user.password)

        if (isPasswordMatch.not()) {
            call.respond(HttpStatusCode.BadRequest, CommonResponse(data = null, message = "Invalid username/password"))
            return@post
        }

        val token = authRepository.generateToken(user)
        call.respond(HttpStatusCode.Created, CommonResponse(data = token, message = "Successfully logged in"))

    }
}