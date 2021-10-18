package com.darklabs.route.note

import com.darklabs.model.CommonResponse
import com.darklabs.model.Note
import com.darklabs.repository.NoteRepository
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.addNote(noteRepository: NoteRepository) {
    post("/note") {
        val requestNote = call.receive<Note>()
        val result = noteRepository.insertNote(requestNote)

        if (result == 1) {
            call.respond(
                HttpStatusCode.OK, CommonResponse(
                    data = "Note successfully inserted", message = "Note successfully inserted"
                )
            )
        } else {
            call.respond(HttpStatusCode.BadRequest, CommonResponse(data = null, message = "Failed to insert note"))
        }
    }
}