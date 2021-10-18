package com.darklabs.db.entity

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

interface User : Entity<User> {
    val id: Int
    val userName: String
    val password: String
}

object UserEntity : Table<User>("user_info") {
    val id = int("id").primaryKey().bindTo { it.id }
    val userName = varchar("username").bindTo { it.userName }
    val password = varchar("password").bindTo { it.password }
}
