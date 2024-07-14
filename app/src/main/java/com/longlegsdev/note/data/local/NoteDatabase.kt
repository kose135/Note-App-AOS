package com.longlegsdev.note.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.longlegsdev.note.data.dao.NoteDao
import com.longlegsdev.note.data.entity.NoteEntity

@Database(
    entities = [NoteEntity::class],
    version = 1
)
abstract class NoteDatabase : RoomDatabase() {
    abstract val noteDao: NoteDao
}