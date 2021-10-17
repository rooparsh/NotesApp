package com.darklabs.di

import com.darklabs.repository.AuthRepository
import com.darklabs.repository.AuthRepositoryImpl
import com.darklabs.repository.NoteRepository
import com.darklabs.repository.NoteRepositoryImpl
import org.koin.dsl.module

object RepositoryModule {
    val module = module {
        single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
        single<NoteRepository> { NoteRepositoryImpl(get()) }
    }
}