package com.longlegsdev.note.data.repository

import com.longlegsdev.note.data.dao.NoteDao
import com.longlegsdev.note.data.entity.NoteEntity
import com.longlegsdev.note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao
) : NoteRepository {
    override suspend fun getNoteList(): Flow<List<NoteEntity>> = noteDao.getNoteList()

    override suspend fun getNote(id: Int): NoteEntity? = noteDao.getNoteById(id)

    override suspend fun upsertNote(note: NoteEntity): Long = noteDao.upsertNote(note)

    override suspend fun deleteNote(notes: List<NoteEntity>) = noteDao.deleteNote(*notes.toTypedArray())
}