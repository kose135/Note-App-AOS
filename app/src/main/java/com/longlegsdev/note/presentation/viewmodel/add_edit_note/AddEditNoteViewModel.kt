package com.longlegsdev.note.presentation.viewmodel.add_edit_note

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.longlegsdev.note.data.entity.NoteEntity
import com.longlegsdev.note.domain.usecase.NoteUseCases
import com.longlegsdev.note.presentation.theme.RedOrange
import com.longlegsdev.note.presentation.viewmodel.add_edit_note.state.NoteState
import com.longlegsdev.note.presentation.viewmodel.add_edit_note.event.AddEditNoteEvent
import com.longlegsdev.note.util.Time
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    arguments: SavedStateHandle,
    private val noteUseCases: NoteUseCases
) : ViewModel() {

    private val _noteState = mutableStateOf(NoteState())
    val noteState: State<NoteState> = _noteState
    init {

        arguments.get<Int>("noteId")?.let { noteId ->
            Timber.i("noteId = $noteId")
            getNote(noteId)
        }
    }

    fun onAddEditNoteEvent(event: AddEditNoteEvent) {
        when (event) {
            is AddEditNoteEvent.Title -> {
                _noteState.value = _noteState.value.copy(
                    note = _noteState.value.note!!.copy(
                        title = event.title
                    )
                )
            }

            is AddEditNoteEvent.Text -> {
                _noteState.value = _noteState.value.copy(
                    note = _noteState.value.note!!.copy(
                        text = event.text
                    )
                )
            }

            is AddEditNoteEvent.Color -> {
                _noteState.value = _noteState.value.copy(
                    note = _noteState.value.note!!.copy(
                        color = event.color
                    )
                )
            }

            is AddEditNoteEvent.UpsertNote -> {
                viewModelScope.launch {
                    _noteState.value = _noteState.value.copy(
                        isLoading = true,
                    )

                    try {
                        noteUseCases.upsertNote(event.note)

                        _noteState.value = _noteState.value.copy(
                            isLoading = false,
                            onSuccess = true
                        )
                    } catch (e: Exception) {
                        _noteState.value = _noteState.value.copy(
                            isLoading = false,
                            errorMessage = e.message
                        )
                    }
                }
            }
        }
    }

    fun getNote(id: Int) {
        Timber.d("Get Note id: $id")
        _noteState.value = NoteState()

        if (id == -1) {
            _noteState.value = NoteState(
                isLoading = false,
                note = NoteEntity(
                    title = "",
                    text = "",
                    color = RedOrange.toArgb(),
                    update = Time.currentTime,
                    create = Time.currentTime
                )
            )
        } else {
            viewModelScope.launch {
                noteUseCases.getNote(id).also { note ->
                    _noteState.value = NoteState(
                        isLoading = false,
                        note = note
                    )
                }
            }
        }
    }
}