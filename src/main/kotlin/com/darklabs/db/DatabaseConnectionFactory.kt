package com.darklabs.db

import org.ktorm.database.Database
import java.net.URI

class DatabaseConnectionFactory {
    lateinit var database: Database
    private val uri = URI(System.getenv("DATABASE_URL"))
    private val uriArray = uri.userInfo.split(":").toTypedArray()
    private val userName = uriArray[0]
    private val password = uriArray[1]

    fun init() {
        database = Database.connect(
            url = "jdbc:postgresssql://${uri.host}:${uri.port}${uri.path}",
            driver = "org.postgresql.Driver",
            user = userName,
            password = password
        )
    }
}