package com.longlegsdev.note.di

import com.longlegsdev.note.domain.repository.NoteRepository
import com.longlegsdev.note.domain.usecase.NoteUseCases
import com.longlegsdev.note.domain.usecase.note.UpsertNoteUseCase
import com.longlegsdev.note.domain.usecase.note.DeleteNoteUseCase
import com.longlegsdev.note.domain.usecase.note.GetNoteListUseCase
import com.longlegsdev.note.domain.usecase.note.GetNoteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getNote = GetNoteUseCase(repository),
            getNoteList = GetNoteListUseCase(repository),
            deleteNote = DeleteNoteUseCase(repository),
            upsertNote = UpsertNoteUseCase(repository),
        )
    }
}