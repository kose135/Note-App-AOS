package com.longlegsdev.note.presentation.screen.note

import android.annotation.SuppressLint
import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.jong.compose.ui.navigation.Screen
import com.longlegsdev.note.data.entity.NoteEntity
import com.longlegsdev.note.presentation.screen.common.LoadingBar
import com.longlegsdev.note.presentation.screen.note.component.NoteFloatingButton
import com.longlegsdev.note.presentation.screen.note.section.NoteContentSection
import com.longlegsdev.note.presentation.screen.note.section.SearchBar
import com.longlegsdev.note.presentation.screen.note.section.SelectAndSortBar
import com.longlegsdev.note.presentation.viewmodel.note.NoteViewModel
import com.longlegsdev.note.presentation.viewmodel.note.event.NoteEvent
import com.longlegsdev.note.presentation.viewmodel.note.event.sort.OrderBy
import com.longlegsdev.note.presentation.viewmodel.note.event.sort.SortBy

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NoteScreen(
    navController: NavHostController,
    viewModel: NoteViewModel = hiltViewModel(),
    activity: Activity? = LocalContext.current as? Activity
) {
    val noteListState = viewModel.noteListState.value

    val sortType = noteListState.sortBy
    val notes = noteListState.notes
    val isLoading = noteListState.isLoading

    var query by rememberSaveable { mutableStateOf("") }

    var deleteEnable by remember { mutableStateOf(false) }
    val selectedNotes = remember { mutableStateListOf<NoteEntity>() }

    /* back key event */
    BackHandler {
        if (deleteEnable) {
            deleteEnable = false
            selectedNotes.clear()
        } else {
            activity?.finish()
        }
    }

    LaunchedEffect(query, sortType) {
        if (query.isNotEmpty()) {
            viewModel.onNoteEvent(NoteEvent.Search(query))
        }

        if (sortType != SortBy.Update(OrderBy.Descending)) {
            viewModel.onNoteEvent(NoteEvent.Sort(sortType))
        }
    }

    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .background(Color.Transparent)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 15.dp, end = 15.dp, top = 5.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {

                SearchBar(
                    Modifier
                        .height(50.dp),
                    query
                ) { newQuery ->
                    query = newQuery
                    viewModel.onNoteEvent(NoteEvent.Search(newQuery))
                }

                SelectAndSortBar(
                    modifier = Modifier
                        .height(56.dp),
                    deleteEnable = deleteEnable,
                    selectedCount = selectedNotes.size,
                    isAll = notes.equals(selectedNotes),
                    clickAll = { isAll ->
                        selectedNotes.clear()

                        if (isAll) selectedNotes.addAll(notes)
                    },
                    selectedSortBy = sortType
                ) { sortType ->
                    viewModel.onNoteEvent(NoteEvent.Sort(sortType))
                }

                NoteContentSection(
                    notes = notes,
                    deleteEnable = deleteEnable,
                    selectedNotes = selectedNotes,
                    onNoteClick = { note ->
                        if (deleteEnable) {
                            if (selectedNotes.contains(note)) {
                                selectedNotes -= note
                            } else {
                                selectedNotes += note
                            }
                        } else {
                            navController.navigate(Screen.AddEditNote.route + "/${note.id}")
                        }
                    },
                    onNoteLongClick = { note ->
                        deleteEnable = true
                        selectedNotes += note
                    }
                )
            }
        }

        if (isLoading) {
            LoadingBar(
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }

        NoteFloatingButton(
            modifier = Modifier
                .align(Alignment.BottomEnd),
            deleteEnable = deleteEnable
        ) {
            if (deleteEnable) {
                viewModel.onNoteEvent(NoteEvent.DeleteNote(selectedNotes))
                deleteEnable = false
            } else {
                navController.navigate(Screen.AddEditNote.route + "/-1")
            }
        }
    }
}

