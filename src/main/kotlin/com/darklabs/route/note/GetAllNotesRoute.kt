package com.darklabs.route.note

import com.darklabs.model.CommonResponse
import com.darklabs.repository.NoteRepository
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.getAllNotes(noteRepository: NoteRepository) {
    get("/notes") {
        val notes = noteRepository.getAllNotes()
        call.respond(
            HttpStatusCode.OK, CommonResponse(
                data = notes, message = "Success"
            )
        )
    }
}