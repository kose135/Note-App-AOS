package com.longlegsdev.note.di

import android.content.Context
import androidx.room.Room
import com.longlegsdev.note.data.dao.NoteDao
import com.longlegsdev.note.data.local.NoteDatabase
import com.longlegsdev.note.util.Note
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext app: Context,
    ): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            Note.DATABASE_NAME
        ).run {
            build()
        }
    }

    @Provides
    fun providePokemonDao(database: NoteDatabase): NoteDao {
        return database.noteDao
    }
}