package com.longlegsdev.note.presentation.screen.note.section

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.longlegsdev.note.R
import com.longlegsdev.note.data.entity.NoteEntity
import com.longlegsdev.note.presentation.screen.note.component.NoteCard

@Composable
fun NoteContentSection(
    notes: List<NoteEntity>,
    deleteEnable: Boolean,
    selectedNotes: List<NoteEntity>,
    onNoteClick: (NoteEntity) -> Unit,
    onNoteLongClick: (NoteEntity) -> Unit
) {
    if (notes.isEmpty()) {
        EmptyNoteView()
    } else {
        NoteListView(
            notes = notes,
            deleteEnable = deleteEnable,
            selectedNotes = selectedNotes,
            onNoteClick = { onNoteClick(it) },
            onNoteLongClick = { onNoteLongClick(it) }
        )
    }
}

@Composable
fun EmptyNoteView(
) {
    Column(
        modifier = Modifier
            .background(Color.Transparent)
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .background(Color.Transparent)
                .align(Alignment.CenterHorizontally)
                .size(150.dp),
            painter = painterResource(R.drawable.img_note),
            contentDescription = "Note Image",
        )

        Text(
            text = stringResource(R.string.str_empty_note)
        )
    }
}

@Composable
fun NoteListView(
    notes: List<NoteEntity>,
    deleteEnable: Boolean,
    selectedNotes: List<NoteEntity>,
    onNoteClick: (NoteEntity) -> Unit,
    onNoteLongClick: (NoteEntity) -> Unit
) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(notes.size) { index ->
            val note = notes[index]

            NoteCard(
                note = note,
                deleteEnable = deleteEnable,
                isSelected = selectedNotes.contains(note),
                click = { onNoteClick(note) },
                longClick = { onNoteLongClick(note) }
            )
        }
    }
}
