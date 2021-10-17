package com.darklabs.plugin

import io.ktor.application.*
import io.ktor.features.*

fun Application.configureLogging() {
    install(DefaultHeaders)
    install(CallLogging)
}