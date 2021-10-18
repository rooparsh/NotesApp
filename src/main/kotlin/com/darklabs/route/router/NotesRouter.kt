package com.darklabs.route.router

import com.darklabs.repository.NoteRepository
import com.darklabs.route.note.*
import io.ktor.auth.*
import io.ktor.routing.*

fun Routing.notesRouter(noteRepository: NoteRepository) {
    authenticate {
        getAllNotes(noteRepository)
        addNote(noteRepository)
        getNoteById(noteRepository)
        updateNoteById(noteRepository)
        deleteNoteById(noteRepository)
    }
}