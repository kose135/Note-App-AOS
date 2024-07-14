package com.longlegsdev.note.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NoteEntity(
    val title: String,
    val text: String,
    val color: Int,
    val update: String,
    val create: String,
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
)