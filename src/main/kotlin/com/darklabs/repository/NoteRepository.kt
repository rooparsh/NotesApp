package com.darklabs.repository

import com.darklabs.db.entity.NoteEntity
import com.darklabs.model.Note
import org.ktorm.database.Database
import org.ktorm.dsl.from
import org.ktorm.dsl.insert
import org.ktorm.dsl.map
import org.ktorm.dsl.select

interface NoteRepository {
    suspend fun getAllNotes(): List<Note>
    suspend fun insertNote(note: Note): Int
}

class NoteRepositoryImpl(private val db: Database) : NoteRepository {

    override suspend fun getAllNotes(): List<Note> {
        return db.from(NoteEntity).select().map { Note(it[NoteEntity.id] ?: -1, it[NoteEntity.note].orEmpty()) }
    }

    override suspend fun insertNote(note: Note): Int {
        return db.insert(NoteEntity) {
            set(NoteEntity.note, note.note)
        }
    }

}