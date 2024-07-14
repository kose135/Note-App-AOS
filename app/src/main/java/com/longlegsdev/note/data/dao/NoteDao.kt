package com.longlegsdev.note.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.longlegsdev.note.data.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM NoteEntity ORDER BY `update` DESC")
    fun getNoteList(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM NoteEntity WHERE id = :id")
    suspend fun getNoteById(id: Int): NoteEntity?

    @Upsert // insert & update
    suspend fun upsertNote(noteEntity: NoteEntity): Long

    @Delete
    suspend fun deleteNote(vararg notes: NoteEntity)
}