package com.longlegsdev.note.presentation.viewmodel.add_edit_note.state

import androidx.compose.ui.graphics.toArgb
import com.longlegsdev.note.data.entity.NoteEntity
import com.longlegsdev.note.presentation.theme.RedOrange

data class NoteState(
    val isLoading: Boolean = true,
    val note: NoteEntity? = null,
    val onSuccess: Boolean = false,
    val errorMessage: String? = null
)