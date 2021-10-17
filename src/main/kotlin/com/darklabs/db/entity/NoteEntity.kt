package com.darklabs.db.entity

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

interface Note : Entity<Note> {
    val id: Int
    val note: String
}

object NoteEntity : Table<Note>("note") {
    val id = int("id").primaryKey().bindTo { it.id }
    val note = varchar("note").bindTo { it.note }
}
