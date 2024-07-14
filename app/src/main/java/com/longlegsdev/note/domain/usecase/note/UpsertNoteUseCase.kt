package com.longlegsdev.note.domain.usecase.note

import com.longlegsdev.note.data.entity.NoteEntity
import com.longlegsdev.note.domain.repository.NoteRepository
import javax.inject.Inject

class UpsertNoteUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(note: NoteEntity): Long = noteRepository.upsertNote(note)
}