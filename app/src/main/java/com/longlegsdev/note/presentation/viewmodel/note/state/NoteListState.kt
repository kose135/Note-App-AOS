package com.longlegsdev.note.presentation.viewmodel.note.state

import com.longlegsdev.note.data.entity.NoteEntity
import com.longlegsdev.note.presentation.viewmodel.note.event.sort.OrderBy
import com.longlegsdev.note.presentation.viewmodel.note.event.sort.SortBy

data class NoteListState(
    val notes: List<NoteEntity> = emptyList(),
    val isLoading: Boolean = false,
    val sortBy: SortBy = SortBy.Update(OrderBy.Descending)
)