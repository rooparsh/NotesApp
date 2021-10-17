package com.darklabs.db

import io.ktor.config.*
import org.ktorm.database.Database

class DatabaseConnectionFactory(private val config: HoconApplicationConfig) {

    lateinit var database: Database

    fun init() {
        database = Database.connect(
            url = config.property("db.jdbcUrl").getString(),
            driver = config.property("db.driver").getString(),
            user = config.property("db.dbUser").getString(),
            password = config.property("db.dbPassword").getString()
        )
    }
}