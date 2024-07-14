package com.longlegsdev.note.domain.usecase

import com.longlegsdev.note.domain.usecase.note.UpsertNoteUseCase
import com.longlegsdev.note.domain.usecase.note.DeleteNoteUseCase
import com.longlegsdev.note.domain.usecase.note.GetNoteUseCase
import com.longlegsdev.note.domain.usecase.note.GetNoteListUseCase

data class NoteUseCases(
    val getNote: GetNoteUseCase,
    val getNoteList: GetNoteListUseCase,
    val deleteNote: DeleteNoteUseCase,
    val upsertNote: UpsertNoteUseCase,
)