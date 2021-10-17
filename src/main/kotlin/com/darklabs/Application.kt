package com.darklabs

import com.darklabs.plugin.configureAuthentication
import com.darklabs.plugin.configureContentNegotiation
import com.darklabs.plugin.configureDi
import com.darklabs.plugin.configureLogging
import com.darklabs.route.configureRouting
import io.ktor.application.*
import io.ktor.server.netty.*
import org.koin.ktor.ext.get

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    configureDi()
    configureLogging()
    configureAuthentication(get())
    configureContentNegotiation()
    configureRouting(get(), get())
}
