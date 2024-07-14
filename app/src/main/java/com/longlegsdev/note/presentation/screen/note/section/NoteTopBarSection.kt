package com.longlegsdev.note.presentation.screen.note.section

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.longlegsdev.note.R
import com.longlegsdev.note.presentation.screen.note.component.AnimatedCircleWithCheck
import com.longlegsdev.note.presentation.screen.note.component.SortTypeDropDownButton
import com.longlegsdev.note.presentation.viewmodel.note.event.sort.OrderBy
import com.longlegsdev.note.presentation.viewmodel.note.event.sort.SortBy
import com.longlegsdev.note.util.Space
import com.longlegsdev.note.util.click


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchBar(
    modifier: Modifier,
    query: String,
    onQueryChanged: (String) -> Unit
) {
    var active by remember { mutableStateOf(false) }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Space(width = 15.dp)

        DockedSearchBar(
            query = query,
            onQueryChange = {
                onQueryChanged(it)
            },
            onSearch = {
                active = false
                onQueryChanged(it)
            },
            active = active,
            onActiveChange = { active = it },
            placeholder = {
                Text(
                    text = stringResource(R.string.str_search),
                    fontSize = 15.sp
                )
            },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
            },
            trailingIcon = {
                if (active)
                    Icon(
                        modifier = Modifier.click {
                            if (query.isNotEmpty()) {
                                onQueryChanged("")
                            } else {
                                active = false
                            }
                        },
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close Icon"
                    )
            },
        ) {

        }
    }
}

@Composable
fun SelectAndSortBar(
    modifier: Modifier,
    deleteEnable: Boolean,
    isAll: Boolean,
    selectedCount: Int,
    clickAll: (Boolean) -> Unit,
    selectedSortBy: SortBy,
    changeSortType: (SortBy) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (deleteEnable) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Row(
                    modifier = Modifier.click { clickAll(!isAll) },
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    AnimatedCircleWithCheck(
                        modifier = Modifier,
                        isSelected = isAll,
                        width = 2.dp,
                        circleSize = 15.dp,
                        padding = 1.dp,
                        color = Color.Black
                    )

                    Space(width = 4.dp)

                    Text(
                        stringResource(R.string.str_select_all),
                        style = MaterialTheme.typography.labelMedium
                    )
                }

                Space(width = 12.dp)

                Text(
                    stringResource(R.string.str_select_count).format(selectedCount),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        Row(
            modifier = if (deleteEnable) Modifier.wrapContentWidth() else Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {


            SortTypeDropDownButton(
                sortBy = selectedSortBy
            ) {
                changeSortType(it)
            }

            // Asc, Desc Icon
            Image(
                modifier = Modifier
                    .click {
                        changeSortType(
                            when (selectedSortBy.orderBy) {
                                is OrderBy.Ascending -> selectedSortBy.copy(OrderBy.Descending)
                                is OrderBy.Descending -> selectedSortBy.copy(OrderBy.Ascending)
                            }
                        )
                    },
                painter = when (selectedSortBy.orderBy) {
                    is OrderBy.Ascending -> painterResource(R.drawable.icon_asc)
                    is OrderBy.Descending -> painterResource(R.drawable.icon_desc)
                },
                contentDescription = "Asc, Desc Icon",
            )
        }
    }
}