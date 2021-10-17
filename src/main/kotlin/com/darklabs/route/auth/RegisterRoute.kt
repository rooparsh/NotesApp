package com.darklabs.route.auth

import com.darklabs.model.CommonResponse
import com.darklabs.model.User
import com.darklabs.repository.AuthRepository
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.registerRoute(authRepository: AuthRepository) {
    post("/register") {
        val userRequest = call.receive<User>()

        if (userRequest.isValidCred()) {
            call.respond(HttpStatusCode.BadRequest, CommonResponse(data = null, message = "Check validation"))
            return@post
        }

        val doesUserExist = authRepository.checkUserExists(userRequest) != null

        if (doesUserExist) {
            call.respond(HttpStatusCode.BadRequest, CommonResponse(data = null, message = "User already exists"))
            return@post
        }

        authRepository.insertUser(userRequest)

        call.respond(HttpStatusCode.Created, CommonResponse(data = null, message = "Successfully inserted"))

    }
}