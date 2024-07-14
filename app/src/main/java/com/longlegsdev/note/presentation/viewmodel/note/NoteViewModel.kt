package com.longlegsdev.note.presentation.viewmodel.note

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.longlegsdev.note.data.entity.NoteEntity
import com.longlegsdev.note.domain.usecase.NoteUseCases
import com.longlegsdev.note.presentation.viewmodel.note.event.NoteEvent
import com.longlegsdev.note.presentation.viewmodel.note.event.sort.OrderBy
import com.longlegsdev.note.presentation.viewmodel.note.event.sort.SortBy
import com.longlegsdev.note.presentation.viewmodel.note.state.NoteListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel() {

    private val _noteListState = mutableStateOf(NoteListState())
    val noteListState: State<NoteListState> = _noteListState

    private var oriNotes: List<NoteEntity> = emptyList()

    init {
        getNotes()
    }

    fun onNoteEvent(event: NoteEvent) {
        when (event) {
            is NoteEvent.DeleteNote -> {
                viewModelScope.launch {
                    noteUseCases.deleteNote(event.notes)
                }
            }

            is NoteEvent.Sort -> {
                Timber.d("Sort Type is ${event.sortBy}")

                _noteListState.value = _noteListState.value.copy(
                    notes = _noteListState.value.notes.run {
                        when (event.sortBy.orderBy) {
                            is OrderBy.Ascending -> {
                                when (event.sortBy) {
                                    is SortBy.Title -> this.sortedBy { it.title.lowercase() }
                                    is SortBy.Update -> this.sortedBy { it.update }
                                    is SortBy.Create -> this.sortedBy { it.create }
                                }
                            }

                            is OrderBy.Descending -> {
                                when (event.sortBy) {
                                    is SortBy.Title -> this.sortedByDescending { it.title.lowercase() }
                                    is SortBy.Update -> this.sortedByDescending { it.update }
                                    is SortBy.Create -> this.sortedByDescending { it.create }
                                }
                            }
                        }
                    },
                    sortBy = event.sortBy
                )
            }

            is NoteEvent.Search -> {
                val query = event.query

                val filteredNotes = if (query.isEmpty()) {
                    oriNotes
                } else {
                    oriNotes.filter {
                        it.title.contains(
                            query,
                            ignoreCase = true
                        ) || it.text.contains(query, ignoreCase = true)
                    }
                }

                _noteListState.value = _noteListState.value.copy(notes = filteredNotes)
            }
        }
    }

    fun getNotes() {
        _noteListState.value = _noteListState.value.copy(isLoading = true)

        viewModelScope.launch {
            noteUseCases.getNoteList().collect { list ->
                _noteListState.value =
                    _noteListState.value.copy(notes = list, isLoading = false)
                oriNotes = list
            }
        }
    }
}