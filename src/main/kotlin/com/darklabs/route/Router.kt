package com.darklabs.route

import com.darklabs.repository.AuthRepository
import com.darklabs.repository.NoteRepository
import com.darklabs.route.router.authRouter
import com.darklabs.route.router.notesRouter
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

fun Application.configureRouting(
    authRepository: AuthRepository,
    noteRepository: NoteRepository
) {
    routing {

        get("/") {
            call.respondText("Hello World!")
        }

        fileRoute()
        notesRouter(noteRepository)
        authRouter(authRepository)
    }
}
