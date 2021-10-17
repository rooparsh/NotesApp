package com.darklabs.db.entity

import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object UserEntity : Table<Nothing>("user") {
    val id = int("id").primaryKey()
    val userName = varchar("username")
    val password = varchar("password")
}
