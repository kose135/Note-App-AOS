package com.jong.compose.ui.navigation

sealed class Screen(val route: String) {

    object Splash: Screen("splash_screen")

    object Note: Screen("note_screen")

    object AddEditNote: Screen("add_edit_note_screen")

}