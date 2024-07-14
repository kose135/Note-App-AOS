package com.longlegsdev.note.presentation.screen.note.component

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.longlegsdev.note.R
import com.longlegsdev.note.presentation.viewmodel.note.event.sort.SortBy
import com.longlegsdev.note.util.Space

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SortTypeDropDownButton(
    sortBy: SortBy,
    sortTypeChange: (SortBy) -> Unit,
) {
    var isExpanded by remember { mutableStateOf(false) }
    val strSortBy = when (sortBy) {
        is SortBy.Title -> stringResource(R.string.str_sort_type_title)
        is SortBy.Update -> stringResource(R.string.str_sort_type_update)
        is SortBy.Create -> stringResource(R.string.str_sort_type_create)
    }

    Box {
        Button(
            colors = ButtonColors(
                containerColor = Color.Transparent,
                contentColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                disabledContentColor = Color.Transparent
            ),
            onClick = { isExpanded = true },
            shape = RectangleShape
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.icon_subject),
                    contentDescription = "drop down menu icon",
                    tint = Color.Black
                )
                Space(width = 7.dp)
                Text(
                    text = strSortBy,
                    color = Color.Black
                )
            }
        }

        DropdownMenu(
            modifier = Modifier
                .background(Color.White)
                .wrapContentSize(),
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
        ) {

            DropdownMenuItem(
                sortBy is SortBy.Title,
                stringResource(R.string.str_sort_type_title)
            ) {
                sortTypeChange(SortBy.Title(sortBy.orderBy))
                isExpanded = false
            }

            DropdownMenuItem(
                sortBy is SortBy.Update,
                stringResource(R.string.str_sort_type_update)
            ) {
                sortTypeChange(SortBy.Update(sortBy.orderBy))
                isExpanded = false
            }

            DropdownMenuItem(
                sortBy is SortBy.Create,
                stringResource(R.string.str_sort_type_create)
            ) {
                sortTypeChange(SortBy.Create(sortBy.orderBy))
                isExpanded = false
            }

        }
    }
}

