package com.darklabs.route.note

import com.darklabs.model.CommonResponse
import com.darklabs.repository.NoteRepository
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.getNoteById(noteRepository: NoteRepository) {
    get("/notes/{id}") {
        val id = call.parameters["id"]?.toInt() ?: -1
        val note = noteRepository.getNoteById(id)
        note?.let { safeNote ->
            call.respond(
                HttpStatusCode.OK, CommonResponse(
                    data = safeNote, message = "Success"
                )
            )
        } ?: kotlin.run {
            call.respond(
                HttpStatusCode.NotFound,
                CommonResponse(
                    data = null, message = "Id not found"
                )
            )
        }
    }
}