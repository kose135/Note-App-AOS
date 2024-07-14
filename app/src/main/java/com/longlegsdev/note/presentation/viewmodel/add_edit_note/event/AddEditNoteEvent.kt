package com.longlegsdev.note.presentation.viewmodel.add_edit_note.event

import com.longlegsdev.note.data.entity.NoteEntity

sealed class AddEditNoteEvent {
    data class Title(val title: String): AddEditNoteEvent()
    data class Text(val text: String): AddEditNoteEvent()
    data class Color(val color: Int): AddEditNoteEvent()

    data class UpsertNote(val note: NoteEntity): AddEditNoteEvent()
}