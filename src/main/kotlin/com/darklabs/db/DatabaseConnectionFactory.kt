package com.darklabs.db

import io.ktor.config.*
import org.ktorm.database.Database

class DatabaseConnectionFactory(private val config: ApplicationConfig) {
    lateinit var database: Database
    fun init() {
        database = Database.connect(
            url = config.property("db.jdbc_db_url").getString()
        )
    }
}