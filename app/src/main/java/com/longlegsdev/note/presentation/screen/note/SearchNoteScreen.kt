package com.longlegsdev.note.presentation.screen.note

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.longlegsdev.note.presentation.viewmodel.note.NoteViewModel

@Composable
fun SearchNoteScreen (
    navController: NavHostController,
    viewModel: NoteViewModel = hiltViewModel()
){
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Search Note Screen")
    }
}