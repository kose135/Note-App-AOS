package com.longlegsdev.note.presentation.screen.add_edit_note

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.longlegsdev.note.R
import com.longlegsdev.note.data.entity.NoteEntity
import com.longlegsdev.note.presentation.screen.add_edit_note.component.AddEditBackTopBar
import com.longlegsdev.note.presentation.screen.add_edit_note.component.AddEditColorList
import com.longlegsdev.note.presentation.screen.common.LoadingBar
import com.longlegsdev.note.presentation.screen.common.TextFieldTransparent
import com.longlegsdev.note.presentation.theme.RedOrange
import com.longlegsdev.note.presentation.viewmodel.add_edit_note.AddEditNoteViewModel
import com.longlegsdev.note.presentation.viewmodel.add_edit_note.event.AddEditNoteEvent
import com.longlegsdev.note.util.Time
import com.longlegsdev.note.util.back
import com.longlegsdev.note.util.click
import timber.log.Timber

@SuppressLint("RememberReturnType", "AutoboxingStateCreation")
@Composable
fun AddEditNoteScreen(
    navController: NavHostController,
    viewModel: AddEditNoteViewModel = hiltViewModel()
) {
    val noteState = viewModel.noteState.value
    val isLoading = noteState.isLoading

    val title = noteState.note?.title ?: ""
    val text = noteState.note?.text ?: ""
    val color = noteState.note?.color ?: RedOrange.toArgb()

    val focusRequester = remember { FocusRequester() }
    var isTextFocused by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val onSuccess = noteState.onSuccess
    val errorMessage = noteState.errorMessage

    if (onSuccess) {
        navController.back()
    } else if (errorMessage != null) {
        navController.back()
        Toast.makeText(context, stringResource(R.string.msg_upsert_failure), Toast.LENGTH_SHORT)
            .show()
        Timber.e("error Message : $errorMessage")
    }

    Column(
        modifier = Modifier
            .background(Color(color))
            .fillMaxSize(),
        horizontalAlignment = Alignment.Start
    ) {
        AddEditBackTopBar(
            title = title,
            backButtonClick = {
                navController.back()
            },
            saveButtonClick = {
                val saveNote: NoteEntity = if (noteState.note!!.id == null) {
                    noteState.note.copy(
                        update = Time.currentTime,
                        create = Time.currentTime
                    )
                } else {
                    noteState.note.copy(
                        update = Time.currentTime,
                    )
                }

                viewModel.onAddEditNoteEvent(AddEditNoteEvent.UpsertNote(saveNote))
            },
            changeTitle = {
                viewModel.onAddEditNoteEvent(AddEditNoteEvent.Title(it))
            }
        )

        AddEditColorList(
            selectColorRgb = color,
            changeColorRgb = { rgb -> viewModel.onAddEditNoteEvent(AddEditNoteEvent.Color(rgb)) }
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .click { focusRequester.requestFocus() },
            contentAlignment = Alignment.TopStart
        ) {
            SelectionContainer {
                TextField(
                    modifier = Modifier
                        .focusRequester(focusRequester)
                        .onFocusChanged { focusState -> isTextFocused = focusState.isFocused },
                    value = text,
                    onValueChange = { viewModel.onAddEditNoteEvent(AddEditNoteEvent.Text(it)) },
                    placeholder = {
                        if (!isTextFocused) {
                            Text(stringResource(R.string.str_text))
                        }
                    },
                    colors = TextFieldTransparent(),
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Start)
                )
            }

            if (isLoading) {
                LoadingBar(
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }
    }
}