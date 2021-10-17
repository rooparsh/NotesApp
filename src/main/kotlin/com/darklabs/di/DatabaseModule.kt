package com.darklabs.di

import com.darklabs.db.DatabaseConnectionFactory
import org.koin.dsl.module

object DatabaseModule {
    val module = module {
        single { DatabaseConnectionFactory(get()).apply { init() }.database }
    }
}