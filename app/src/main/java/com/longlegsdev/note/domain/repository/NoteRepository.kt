package com.longlegsdev.note.domain.repository

import com.longlegsdev.note.data.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    suspend fun getNoteList(): Flow<List<NoteEntity>>

    suspend fun getNote(id: Int): NoteEntity?

    suspend fun upsertNote(note: NoteEntity): Long

    suspend fun deleteNote(notes: List<NoteEntity>)
}