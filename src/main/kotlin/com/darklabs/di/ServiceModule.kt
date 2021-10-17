package com.darklabs.di

import com.darklabs.service.TokenManager
import com.typesafe.config.ConfigFactory
import io.ktor.config.*
import org.koin.dsl.module

object ServiceModule {
    val module = module {
        single { HoconApplicationConfig(ConfigFactory.load()) }
        single { TokenManager(get()) }
    }
}