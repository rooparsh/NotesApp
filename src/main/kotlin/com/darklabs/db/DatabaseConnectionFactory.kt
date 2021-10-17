package com.darklabs.db

import org.ktorm.database.Database

class DatabaseConnectionFactory {
    lateinit var database: Database
    fun init() {
        database = Database.connect(
            url = System.getenv("JDBC_DATABASE_URL")
        )
    }
}