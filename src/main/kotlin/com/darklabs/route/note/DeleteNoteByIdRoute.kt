package com.darklabs.route.note

import com.darklabs.model.CommonResponse
import com.darklabs.repository.NoteRepository
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.deleteNoteById(noteRepository: NoteRepository) {
    delete("/notes/{id}") {
        val id = call.parameters["id"]?.toInt() ?: -1
        val result = noteRepository.deleteNoteById(id)


        if (result == 1) {
            call.respond(
                HttpStatusCode.OK, CommonResponse(
                    data = "Note successfully deleted", message = "Note successfully deleted"
                )
            )
        } else {
            call.respond(HttpStatusCode.BadRequest, CommonResponse(data = null, message = "Failed to delete note"))
        }
    }
}