package com.longlegsdev.note.domain.usecase.note

import com.longlegsdev.note.data.entity.NoteEntity
import com.longlegsdev.note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNoteListUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(): Flow<List<NoteEntity>> = noteRepository.getNoteList()
}
