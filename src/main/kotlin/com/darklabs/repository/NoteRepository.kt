package com.darklabs.repository

import com.darklabs.db.entity.NoteEntity
import com.darklabs.model.Note
import org.ktorm.database.Database
import org.ktorm.dsl.*

interface NoteRepository {
    suspend fun getAllNotes(): List<Note>
    suspend fun getNoteById(id: Int): Note?
    suspend fun deleteNoteById(id: Int): Int
    suspend fun updateNote(id: Int, note: Note): Int
    suspend fun insertNote(note: Note): Int
}

class NoteRepositoryImpl(private val db: Database) : NoteRepository {

    override suspend fun getAllNotes(): List<Note> {
        return db.from(NoteEntity)
            .select()
            .map { Note(it[NoteEntity.id] ?: -1, it[NoteEntity.note].orEmpty()) }
    }

    override suspend fun getNoteById(id: Int): Note? {
        return db.from(NoteEntity)
            .select()
            .where { NoteEntity.id eq id }
            .map { Note(it[NoteEntity.id] ?: -1, it[NoteEntity.note].orEmpty()) }
            .firstOrNull()
    }

    override suspend fun deleteNoteById(id: Int): Int {
        return db.delete(NoteEntity) {
            it.id eq id
        }

    }

    override suspend fun updateNote(id: Int, note: Note): Int {
        return db.update(NoteEntity) {
            set(it.note, note.note)
            where { it.id eq id }
        }
    }

    override suspend fun insertNote(note: Note): Int {
        return db.insert(NoteEntity) {
            set(NoteEntity.note, note.note)
        }
    }

}