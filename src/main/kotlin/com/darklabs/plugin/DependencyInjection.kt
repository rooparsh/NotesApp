package com.darklabs.plugin

import com.darklabs.di.DatabaseModule
import com.darklabs.di.RepositoryModule
import com.darklabs.di.ServiceModule
import io.ktor.application.*
import org.koin.ktor.ext.Koin
import org.koin.logger.SLF4JLogger

fun Application.configureDi() {
    install(Koin) {
        SLF4JLogger()
        modules(ServiceModule.module, DatabaseModule.module, RepositoryModule.module)
    }
}