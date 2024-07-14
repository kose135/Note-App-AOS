package com.longlegsdev.note.presentation.viewmodel.note.event

import com.longlegsdev.note.data.entity.NoteEntity
import com.longlegsdev.note.presentation.viewmodel.note.event.sort.SortBy

sealed class NoteEvent {
    data class DeleteNote(val notes: List<NoteEntity>): NoteEvent()
    data class Sort(val sortBy: SortBy): NoteEvent()
    data class Search(val query: String): NoteEvent()
}