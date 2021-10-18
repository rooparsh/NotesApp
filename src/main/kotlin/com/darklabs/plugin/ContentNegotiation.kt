package com.darklabs.plugin

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.serialization.*
import kotlinx.serialization.json.Json

fun Application.configureContentNegotiation() {
    install(ContentNegotiation) {
        json(Json {
            encodeDefaults = false
            ignoreUnknownKeys = true
            isLenient = true
        })
    }
}