package com.longlegsdev.note.di

import com.longlegsdev.note.data.repository.NoteRepositoryImpl
import com.longlegsdev.note.domain.repository.NoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {
    @Binds
    fun bindsNoteRepository(
        noteRepositoryImpl: NoteRepositoryImpl
    ): NoteRepository
}