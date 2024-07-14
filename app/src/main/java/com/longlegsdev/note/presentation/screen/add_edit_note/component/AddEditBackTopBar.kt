package com.longlegsdev.note.presentation.screen.add_edit_note.component

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.longlegsdev.note.R
import com.longlegsdev.note.presentation.screen.common.TextFieldTransparent

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddEditBackTopBar(
    title: String? = null,
    backButtonClick: () -> Unit,
    saveButtonClick: () -> Unit,
    changeTitle: (String) -> Unit
) {
    var isTitleFocused by remember { mutableStateOf(false) }

    TopAppBar(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.White),
        title = {
            SelectionContainer {
                TextField(
                    modifier = Modifier
                        .onFocusChanged { focusState -> isTitleFocused = focusState.isFocused },
                    value = title ?: "",
                    onValueChange = { textValue -> changeTitle(textValue) },
                    singleLine = true,
                    placeholder = {
                        if (!isTitleFocused) {
                            Text(stringResource(R.string.str_title))
                        }
                    },
                    colors = TextFieldTransparent()
                )
            }
        },
        navigationIcon = {
            IconButton(
                onClick = { backButtonClick() }
            ) {
                Image(
                    painter = painterResource(R.drawable.icon_back),
                    contentDescription = "back button icon"
                )
            }
        },
        actions = {
            IconButton(onClick = { saveButtonClick() }) {
                Image(
                    painter = painterResource(R.drawable.icon_save),
                    contentDescription = "save button icon"
                )
            }
        },
    )
}