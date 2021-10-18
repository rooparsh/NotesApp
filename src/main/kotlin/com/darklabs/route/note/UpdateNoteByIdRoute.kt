package com.darklabs.route.note

import com.darklabs.model.CommonResponse
import com.darklabs.model.Note
import com.darklabs.repository.NoteRepository
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.updateNoteById(noteRepository: NoteRepository) {
    put("/notes/{id}") {
        val id = call.parameters["id"]?.toInt() ?: -1

        val newNote = call.receive<Note>()

        val result = noteRepository.updateNote(id, newNote)

        if (result == 1) {
            call.respond(
                HttpStatusCode.OK, CommonResponse(
                    data = "Note successfully updated", message = "Note successfully updated"
                )
            )
        } else {
            call.respond(HttpStatusCode.BadRequest, CommonResponse(data = null, message = "Failed to update note"))
        }
    }
}